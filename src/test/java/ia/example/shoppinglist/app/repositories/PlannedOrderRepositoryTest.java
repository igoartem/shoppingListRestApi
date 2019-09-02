package ia.example.shoppinglist.app.repositories;

import static org.junit.Assert.*;

import java.time.LocalDateTime;
import java.util.List;

import ia.example.shoppinglist.app.MongoConfig;
import ia.example.shoppinglist.domain.Order;
import ia.example.shoppinglist.domain.PlannedOrder;
import ia.example.shoppinglist.repositories.PlannedOrderRepository;

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
public class PlannedOrderRepositoryTest {

    @Autowired
    private PlannedOrderRepository plannedOrderRepository;

    @Autowired
    MongoOperations mongoTemplate;

    @Before
    public void init() {
        plannedOrderRepository.deleteAll();
        assertEquals(0, plannedOrderRepository.count());
        assertNotNull(plannedOrderRepository);
        mongoTemplate.dropCollection(PlannedOrder.class);
    }

    @Test
    public void findPlannedOrderByUserIdTest() {
        final String userId = "1111";

        PlannedOrder plannedOrder = new PlannedOrder();
        plannedOrder.setUserId(userId);
        plannedOrder.setPlanDate(LocalDateTime.now());
        plannedOrder.setPlanCron("256345");
        plannedOrder.setOrder(TestUtils.createOrder("test", "1244", false));
        plannedOrderRepository.save(plannedOrder);

        List<PlannedOrder> plannedOrders = plannedOrderRepository.findPlannedOrderByUserId(userId);
        assertNotNull(plannedOrders);
        assertEquals(1, plannedOrders.size());
        PlannedOrder plannedOrderBd = plannedOrders.get(0);
        assertNotNull(plannedOrderBd.getId());
        assertEquals(plannedOrder.getPlanCron(), plannedOrderBd.getPlanCron());

        PlannedOrder plannedOrder2 = new PlannedOrder();
        plannedOrder2.setUserId("23124234");
        plannedOrder2.setPlanDate(LocalDateTime.now());
        plannedOrder2.setPlanCron("256345");
        plannedOrder2.setOrder(TestUtils.createOrder("test2", "1244", false));
        plannedOrderRepository.save(plannedOrder);

        plannedOrders = plannedOrderRepository.findPlannedOrderByUserId(userId);
        assertNotNull(plannedOrders);
        assertEquals(1, plannedOrders.size());
    }
}
