package ia.example.shoppinglist.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import ia.example.shoppinglist.domain.Order;
import ia.example.shoppinglist.domain.PlannedOrder;
import ia.example.shoppinglist.repositories.OrderRepository;
import ia.example.shoppinglist.repositories.PlannedOrderRepository;

public class ScheduledCreatorPlannedOrderService {
    private static final Logger log = LoggerFactory.getLogger(ScheduledCreatorPlannedOrderService.class);

    private final PlannedOrderRepository plannedOrderRepository;
    private final OrderRepository orderRepository;

    @Autowired
    public ScheduledCreatorPlannedOrderService(PlannedOrderRepository plannedOrderRepository, OrderRepository orderRepository) {
        this.plannedOrderRepository = plannedOrderRepository;
        this.orderRepository = orderRepository;
    }

    //Получаем планируемые задачи на сегодня и создаем их
    @Scheduled(cron = "*/10 * * * * *")
    public void creatingOrderForDate() {
        List<PlannedOrder> plannedOrders = plannedOrderRepository.findPlannedOrdersByPlanCronIsNotNull();

        plannedOrders.forEach(plannedOrder -> {
            Order order = plannedOrder.getOrder();
            order.setActual(true);
            order.setUserId(plannedOrder.getUserId());
            String newNameOrder = "AutoGentrate" + order.getName();
            order.setName(newNameOrder);
            orderRepository.save(order);
        });
        log.debug("dich");
        System.out.println("I am a schedule-method with fixed rate param.");
        System.out.println("Executes every 1 minute.");
    }

}
