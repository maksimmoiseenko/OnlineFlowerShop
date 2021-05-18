package maksim.moiseenko.services;

import maksim.moiseenko.models.Order;
import maksim.moiseenko.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }
    public List<Order> getAllByAccountId(Long id){
        if(id == 1) return orderRepository.findAll();//если админ, то забираем все заказы
        return orderRepository.findAllByAccount_Id(id);//иначе забираем только свои заказы
    }
    public Order save(Order order){
        return orderRepository.save(order);
    }
    public void delete(Long id){
        orderRepository.deleteById(id);
    }
}
