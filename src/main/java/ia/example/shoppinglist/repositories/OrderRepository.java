package ia.example.shoppinglist.repositories;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ia.example.shoppinglist.domain.Order;

import java.util.List;

@Repository("orderRepository")
public interface OrderRepository extends CrudRepository<Order, String> {
    @Query("{order.client.id: ?0}")
    List<Order> findOrderByUserId(String userId);

    @Query("{$and {order.client.id: ?0}, {order.actual: true}}")
    List<Order> findActulaOrderByUserId(String userId);
}
