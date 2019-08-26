package ia.example.shoppinglist.repositories;

import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ia.example.shoppinglist.domain.Order;

import java.util.List;

@Repository("orderRepository")
public interface OrderRepository extends CrudRepository<Order, String> {
    @Query("{$and { order.client.id: ?0}, {order.actual: true}}")
    List<Order> findOrderByUserId(String userId, Boolean actual);

//    @Query("{$and { order.client.id: ?0}, {order.actual: true}}")
//    List<Order> findActulaOrderByUserId(String userId);
}
