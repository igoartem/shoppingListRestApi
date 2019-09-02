package ia.example.shoppinglist.app;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.validation.BindException;

import ia.example.shoppinglist.app.repositories.TestUtils;
import ia.example.shoppinglist.domain.EntryOrder;
import ia.example.shoppinglist.domain.Order;
import ia.example.shoppinglist.domain.User;
import ia.example.shoppinglist.exeption.EntityNotFoundException;
import ia.example.shoppinglist.repositories.OrderRepository;
import ia.example.shoppinglist.repositories.UserRepository;
import ia.example.shoppinglist.rest.dto.EntryOrderDto;
import ia.example.shoppinglist.rest.dto.OrderDto;
import ia.example.shoppinglist.rest.service.UniversalMapper;
import ia.example.shoppinglist.rest.service.implementations.OrderServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TestConfigService.class, MongoConfig.class, TestConfigUniversalMapper.class })
public class OrderServiceImplTest {
    private static final Logger log = LoggerFactory.getLogger(OrderServiceImplTest.class);

    @Autowired
    private OrderServiceImpl orderService;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UniversalMapper universalMapper;

    @Autowired
    MongoOperations mongoTemplate;

    private String userId;

    @Before
    public void init() {
        orderRepository.deleteAll();
        assertEquals(0, orderRepository.count());
        assertNotNull(orderRepository);
        mongoTemplate.dropCollection(Order.class);

        List<Order> orders = (List<Order>) orderRepository.findAll();
        assertNotNull(orders);
        assertEquals(0, orders.size());

        User user = new User();
        user.setFio("test");
        User userSaved = userRepository.save(user);
        userId = userSaved.getId();
    }

    @Test
    public void findOrdersByUserIdTest() {
        List<OrderDto> orderDtos = orderService.findOrdersByUserId(userId, true, null);
        assertNotNull(orderDtos);
        assertEquals(0, orderDtos.size());

        orderDtos = orderService.findAll();
        assertNotNull(orderDtos);
        assertEquals(0, orderDtos.size());

        Order order = TestUtils.createOrder("testName 1", userId, true);
        orderRepository.save(order);

        orderDtos = orderService.findOrdersByUserId(userId, true, null);
        assertNotNull(orderDtos);
        assertEquals(1, orderDtos.size());

        Order order2 = TestUtils.createOrder("testName 2", userId, true);
        orderRepository.save(order2);

        orderDtos = orderService.findOrdersByUserId(userId, true, null);
        assertNotNull(orderDtos);
        assertEquals(2, orderDtos.size());

        orderDtos = orderService.findOrdersByUserId("tttt", true, null);
        assertNotNull(orderDtos);
        assertEquals(0, orderDtos.size());
    }

    @Test
    public void createOrderTest() {

        OrderDto orderDto = TestUtils.createOrderDto("name", userId, true);
        try {
            orderService.createOrder(orderDto, true, userId);
        } catch (BindException e) {
            log.error("Error create order", e);
        }

        List<Order> orders = (List<Order>) orderRepository.findAll();
        assertNotNull(orders);
        assertEquals(1, orders.size());
        assertNotNull(orders.get(0).getId());
        assertEquals(orderDto.getName(), orders.get(0).getName());
    }

    @Test
    public void updateOrderDtoTest() {

        Order order = TestUtils.createOrder("testName 1", userId, true);
        Order orderBd = orderRepository.save(order);

        OrderDto orderDto = (OrderDto) universalMapper.toDto(orderBd, OrderDto.class);
        orderDto.setName("new Name");

        try {
            orderService.updateOrderDto(orderDto, userId, false);
        } catch (EntityNotFoundException e) {
            log.error("Error update order", e);
        }

        Order chechOrder = orderRepository.findOne(orderBd.getId());
        assertNotNull(chechOrder);
        assertEquals(orderBd.getId(), chechOrder.getId());
        assertEquals(orderDto.getName(), chechOrder.getName());
    }

    @Test
    public void createEntryOrderTest() {
        Order order = TestUtils.createOrder("testName 1", userId, true);
        Order orderBd = orderRepository.save(order);

        EntryOrderDto entryOrderDto = TestUtils.createEntryOrderDto(EntryOrder.StateEntryOrder.NEW, "desc2", "name2");
        orderService.createEntryOrder(userId, orderBd.getId(), entryOrderDto);

        Order orderUpdate = orderRepository.findOne(orderBd.getId());
        assertNotNull(orderUpdate);
        assertNotNull(orderUpdate.getEntryOrders());
        assertEquals(3, orderUpdate.getEntryOrders().size());
        assertEquals(entryOrderDto.getDescription(), orderUpdate.getEntryOrders().get(2).getDescription());
    }

    @Test
    public void updateEntryOrderTest() {
        Order order = TestUtils.createOrder("testName 1", userId, true);
        Order orderBd = orderRepository.save(order);

        EntryOrder entryOrder = orderBd.getEntryOrders().get(0);
        EntryOrderDto entryOrderDto = (EntryOrderDto) universalMapper.toDto(entryOrder, EntryOrderDto.class);
        entryOrderDto.setDescription("new desc");
        orderService.updateEntryOrder(userId, orderBd.getId(), entryOrderDto);

        Order orderUpdate = orderRepository.findOne(orderBd.getId());
        assertNotNull(orderUpdate);
        assertNotNull(orderUpdate.getEntryOrders());
        assertEquals(2, orderUpdate.getEntryOrders().size());
        assertEquals(entryOrderDto.getDescription(), orderUpdate.getEntryOrders().get(1).getDescription());//обновленная запись будет ниже

    }

}
