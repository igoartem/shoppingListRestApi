package ia.example.shoppinglist.service;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import ia.example.shoppinglist.repositories.OrderRepository;
import ia.example.shoppinglist.repositories.PlannedOrderRepository;

@Service
@EnableScheduling
public class PlannedOrderService {

    private final PlannedOrderRepository plannedOrderRepository;
    private final OrderRepository orderRepository;

    public PlannedOrderService(PlannedOrderRepository plannedOrderRepository, OrderRepository orderRepository) {
        this.plannedOrderRepository = plannedOrderRepository;
        this.orderRepository = orderRepository;
    }

    @Scheduled(cron = "0 0/1 0 ? * * *")
    public void creatingOrder() {

        System.out.println("I am a schedule-method with fixed rate param.");
        System.out.println("Executes every 1 minute.");
    }

}
