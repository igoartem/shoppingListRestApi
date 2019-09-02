package ia.example.shoppinglist.app.repositories;

import static org.junit.Assert.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import ia.example.shoppinglist.app.MongoConfig;
import ia.example.shoppinglist.domain.EntryOrder;
import ia.example.shoppinglist.domain.Order;
import ia.example.shoppinglist.domain.Product;
import ia.example.shoppinglist.repositories.OrderRepository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = MongoConfig.class)
@TestExecutionListeners(value = DependencyInjectionTestExecutionListener.class)
public class OrderRepositoryTest {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    MongoOperations mongoTemplate;

    @Before
    public void init() {
        orderRepository.deleteAll();
        assertEquals(0, orderRepository.count());
        assertNotNull(orderRepository);
        mongoTemplate.dropCollection(Order.class);
    }

    @Test
    public void findOrderByUserIdTest() {
        final String userId = "1111";

        Order order = TestUtils.createOrder("testName 1", userId, true);
        orderRepository.save(order);

        List<Order> orders = orderRepository.findOrderByUserId(userId, true);
        assertNotNull(orders);
        assertEquals(1, orders.size());
        Order orderBd = orders.get(0);
        assertNotNull(orderBd.getId());
        assertNotNull(orderBd.getEntryOrders());
        assertEquals(order.getName(), orderBd.getName());
        assertTrue(orderBd.getActual());
        assertEquals(order.getCreateDate(), orderBd.getCreateDate());

        Order order2 = TestUtils.createOrder("testName 2", "1234", true);
        orderRepository.save(order2);

        Order order3 = TestUtils.createOrder("testName 3", userId, false);
        orderRepository.save(order3);

        orders = orderRepository.findOrderByUserId(userId, true);
        assertNotNull(orders);
        assertEquals(1, orders.size());

        Order order4 = TestUtils.createOrder("testName 4", userId, true);
        orderRepository.save(order4);

        orders = orderRepository.findOrderByUserId(userId, true);
        assertNotNull(orders);
        assertEquals(2, orders.size());
        assertEquals(order.getName(), orders.get(0).getName());
        assertEquals(order4.getName(), orders.get(1).getName());
    }


}
