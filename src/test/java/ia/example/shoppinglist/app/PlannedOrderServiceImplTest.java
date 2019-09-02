package ia.example.shoppinglist.app;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import ia.example.shoppinglist.app.repositories.TestUtils;
import ia.example.shoppinglist.domain.Order;
import ia.example.shoppinglist.domain.PlannedOrder;
import ia.example.shoppinglist.domain.User;
import ia.example.shoppinglist.repositories.OrderRepository;
import ia.example.shoppinglist.repositories.PlannedOrderRepository;
import ia.example.shoppinglist.repositories.UserRepository;
import ia.example.shoppinglist.rest.dto.OrderDto;
import ia.example.shoppinglist.rest.dto.PlannedOrderDto;
import ia.example.shoppinglist.rest.service.UniversalMapper;
import ia.example.shoppinglist.rest.service.implementations.PlannedOrderServiceImpl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TestConfigService.class, MongoConfig.class, TestConfigUniversalMapper.class })
public class PlannedOrderServiceImplTest {

    @Autowired
    private PlannedOrderServiceImpl plannedOrderService;

    @Autowired
    private PlannedOrderRepository plannedOrderRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UniversalMapper universalMapper;

    @Autowired
    MongoOperations mongoTemplate;

    private String userId;

    @Before
    public void init() {
        plannedOrderRepository.deleteAll();
        assertEquals(0, plannedOrderRepository.count());
        assertNotNull(plannedOrderRepository);
        mongoTemplate.dropCollection(PlannedOrder.class);

        List<PlannedOrder> plannedOrders = (List<PlannedOrder>) plannedOrderRepository.findAll();
        assertNotNull(plannedOrders);
        assertEquals(0, plannedOrders.size());

        User user = new User();
        user.setFio("test");
        User userSaved = userRepository.save(user);
        userId = userSaved.getId();
    }

    @Test
    public void findOPlannedOrdersByUserIdTest() {
        String userId = "1111";

        List<PlannedOrderDto> plannedOrderDtos = plannedOrderService.findOPlannedOrdersByUserId(userId);
        assertNotNull(plannedOrderDtos);
        assertEquals(0, plannedOrderDtos.size());

        plannedOrderDtos = plannedOrderService.findAll();
        assertNotNull(plannedOrderDtos);
        assertEquals(0, plannedOrderDtos.size());

        PlannedOrder plannedOrder = TestUtils.createPlannedOrder(userId);
        plannedOrderRepository.save(plannedOrder);

        plannedOrderDtos = plannedOrderService.findOPlannedOrdersByUserId(userId);
        assertNotNull(plannedOrderDtos);
        assertEquals(1, plannedOrderDtos.size());

        PlannedOrder plannedOrder1 = TestUtils.createPlannedOrder(userId);
        plannedOrderRepository.save(plannedOrder1);

        plannedOrderDtos = plannedOrderService.findOPlannedOrdersByUserId(userId);
        assertNotNull(plannedOrderDtos);
        assertEquals(2, plannedOrderDtos.size());
    }

    @Test
    public void createPlannedOrderTest() {
        PlannedOrderDto plannedOrderDto = TestUtils.createPlannedOrderDto(userId);
        plannedOrderService.createPlannedOrder(plannedOrderDto, userId);

        List<PlannedOrder> plannedOrders = (List<PlannedOrder>) plannedOrderRepository.findAll();
        assertNotNull(plannedOrders);
        assertEquals(1, plannedOrders.size());
        assertNotNull(plannedOrders.get(0).getId());
        assertEquals(plannedOrderDto.getUserId(), plannedOrders.get(0).getUserId());
    }

    @Test
    public void createPlannedOrderByOrderTest() {

        Order order = TestUtils.createOrder("test", userId, true);
        orderRepository.save(order);

        PlannedOrderDto plannedOrderDto = TestUtils.createPlannedOrderDto(userId);
        plannedOrderService.createPlannedOrderByOrder(plannedOrderDto, userId, order.getId());

        List<PlannedOrder> plannedOrders = (List<PlannedOrder>) plannedOrderRepository.findAll();
        assertNotNull(plannedOrders);
        assertEquals(1, plannedOrders.size());
        assertNotNull(plannedOrders.get(0).getId());
        assertEquals(plannedOrderDto.getUserId(), plannedOrders.get(0).getUserId());
        assertNotNull(plannedOrders.get(0).getOrder());
        assertEquals(order.getName(), plannedOrders.get(0).getOrder().getName());
    }

}
