package maksim.moiseenko.repositories;

import maksim.moiseenko.models.Account;
import maksim.moiseenko.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {
    List<Order> findAllByAccount_Id(Long id);
    List<Order> findAllByCourier(Account courier);
}
