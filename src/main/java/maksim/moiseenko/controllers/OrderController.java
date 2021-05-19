package maksim.moiseenko.controllers;

import maksim.moiseenko.DTO.AccountDto;
import maksim.moiseenko.models.Flower;
import maksim.moiseenko.models.Order;
import maksim.moiseenko.repositories.OrderRepository;
import maksim.moiseenko.security.details.UserDetailsImpl;
import maksim.moiseenko.services.FlowerService;
import maksim.moiseenko.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class OrderController {
    private final OrderService orderService;
    private final FlowerService flowerService;
    @Autowired
    public OrderController(OrderService orderService, FlowerService flowerService){
        this.orderService=orderService;
        this.flowerService = flowerService;
    }
    @GetMapping("/orders")
    public String orders(Authentication authentication, Model model){
        if(authentication==null){
            return "redirect:/signIn";
        }
        UserDetailsImpl userDetails=(UserDetailsImpl)authentication.getPrincipal();
        AccountDto accountDto=AccountDto.from(userDetails.getAccount());
        List<Order> orders = orderService.getAllByAccountId(accountDto.getId());
        int sum=0;
        for(Order order: orders){
            sum+=order.getFlower().getCost();
        }
        model.addAttribute("account",accountDto);
        model.addAttribute("orders", orders);
        model.addAttribute("cost", sum);
        return "order";
    }
    @GetMapping("/order/{id}/delete")
    public String deleteOrder(@PathVariable Long id){
        orderService.delete(id);
        return "redirect:/orders";
    }
    @GetMapping("/order/add/{flowerId}")
    public String deleteFlower(@PathVariable Long flowerId,Authentication authentication){
        if(authentication==null){
            return "redirect:/signIn";
        }
        UserDetailsImpl userDetails=(UserDetailsImpl)authentication.getPrincipal();
        Flower flower = flowerService.getFlower(flowerId).get();
        Order order = new Order(userDetails.getAccount(), flower);
        orderService.save(order);
        return "redirect:/orders";
    }
}
