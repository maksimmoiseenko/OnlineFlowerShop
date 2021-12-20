package maksim.moiseenko.controllers;

import maksim.moiseenko.DTO.AccountDto;
import maksim.moiseenko.models.Flower;
import maksim.moiseenko.models.Order;
import maksim.moiseenko.models.Role;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

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
        List<Order> orders = orderService.getAllByAccount(userDetails.getAccount());
        int sum=0;
        for(Order order: orders){
            sum+=order.getFlower().getCost()*order.getAmount();
        }
        model.addAttribute("account",accountDto);
        model.addAttribute("isCourier", accountDto.getRole().equals(Role.COURIER));
        model.addAttribute("orders", orders);
        model.addAttribute("cost", sum);
        return "order";
    }
    @GetMapping("/order/{id}/delete")
    public String deleteOrder(@PathVariable Long id){
        orderService.delete(id);
        return "redirect:/orders";
    }
    @GetMapping("/order/{id}/addCourier")
    public String addCourier(@PathVariable Long id, Authentication authentication){
        UserDetailsImpl userDetails=(UserDetailsImpl)authentication.getPrincipal();
        Optional<Order> optionalOrder = orderService.getById(id);
        optionalOrder.ifPresent(order -> {
            order.setCourier(userDetails.getAccount());
            orderService.save(order);
        });
        return "redirect:/orders";
    }
    @GetMapping("/order/{id}/deleteCourier")
    public String deleteCourier(@PathVariable Long id, Authentication authentication){
        UserDetailsImpl userDetails=(UserDetailsImpl)authentication.getPrincipal();

        Optional<Order> optionalOrder = orderService.getById(id);
        optionalOrder.ifPresent(order -> {
            if(userDetails.getAccount().getId().equals(order.getCourier().getId())) {
                order.setCourier(null);
                orderService.save(order);
            }
        });
        return "redirect:/orders";
    }
    @GetMapping("/order/add/{flowerId}")
    public String getOrderForm(@PathVariable Long flowerId,
                               Model model){
        model.addAttribute("flowerId",flowerId);
        return "orderForm";
    }
    @PostMapping("/order/add/{flowerId}")
    public String orderAdd(Authentication authentication,
                           @PathVariable Long flowerId,
                           @RequestParam int amount,
                           @RequestParam String paymentMethod,
                           @RequestParam String address,
                           @RequestParam String getting,
                           @RequestParam String telephone){
        System.out.println(flowerId + " " + amount + " " + paymentMethod + " " + address + " " + getting);

        UserDetailsImpl userDetails=(UserDetailsImpl)authentication.getPrincipal();
        Optional<Flower> flower = flowerService.getFlower(flowerId);
        flower.ifPresent(value -> orderService.save(new Order(userDetails.getAccount(),null,
                value, amount, telephone, address, paymentMethod, getting, false )));
        return "redirect:/";
    }
}
