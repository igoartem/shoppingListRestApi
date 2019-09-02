package ia.example.shoppinglist.repositories;

import ia.example.shoppinglist.domain.Order;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("orderRepository")
public interface OrderRepository extends CrudRepository<Order, String> {

    @Query("{ 'userId':?0, 'actual':?1 }")
    List<Order> findOrderByUserId(String userId, Boolean actual);

}
