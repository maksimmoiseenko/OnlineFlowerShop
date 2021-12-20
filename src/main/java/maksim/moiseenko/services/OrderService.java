package maksim.moiseenko.services;

import maksim.moiseenko.DTO.AccountDto;
import maksim.moiseenko.models.Account;
import maksim.moiseenko.models.Order;
import maksim.moiseenko.models.Role;
import maksim.moiseenko.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }
    public List<Order> getAllByAccount(Account account){
        if(account.getRole() == Role.ADMIN) return orderRepository.findAll();//если админ, то забираем все заказы
        if(account.getRole() == Role.COURIER) {
            List<Order> myOrders = orderRepository.findAllByCourier(account);
            return Stream.concat(orderRepository.findAllByCourier(null).stream().filter(order ->
                    order.getGetting().equals("delivery")),myOrders.stream()).collect(Collectors.toList());
        }
        return orderRepository.findAllByAccount_Id(account.getId());//иначе забираем только свои заказы
    }
    public Optional<Order> getById(Long id){ return orderRepository.findById(id);}
    public Order save(Order order){
        return orderRepository.save(order);
    }
    public void delete(Long id){
        orderRepository.deleteById(id);
    }
}
