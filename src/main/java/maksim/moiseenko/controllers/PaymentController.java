package maksim.moiseenko.controllers;

import maksim.moiseenko.DTO.AccountDto;
import maksim.moiseenko.models.Order;
import maksim.moiseenko.security.details.UserDetailsImpl;
import maksim.moiseenko.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Controller
public class PaymentController {
    private OrderService orderService;
    @Autowired
    public PaymentController(OrderService orderService){
        this.orderService = orderService;
    }
    @GetMapping("/payment/{orderId}")
    public String paymentPage(@PathVariable Long orderId, Model model){
        model.addAttribute("orderId",orderId);
        return "payment";
    }
    @GetMapping("/payment/{orderId}/paid")
    public String setPaid(@PathVariable Long orderId,Authentication authentication){
        Optional<Order> optionalOrder = orderService.getById(orderId);
        optionalOrder.ifPresent(order -> {
            order.setPaid(true);
            orderService.save(order);
        });
        return "redirect:/orders";
    }
}
