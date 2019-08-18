package ia.example.shoppinglist.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ia.example.shoppinglist.domain.Order;

@Repository("orderRepository")
public interface OrderRepository extends CrudRepository<Order, String> {
}
