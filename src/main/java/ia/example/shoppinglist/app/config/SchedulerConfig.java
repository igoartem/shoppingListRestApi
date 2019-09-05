package ia.example.shoppinglist.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import ia.example.shoppinglist.repositories.OrderRepository;
import ia.example.shoppinglist.repositories.PlannedOrderRepository;
import ia.example.shoppinglist.service.PlannedOrderService;

@Configuration
@EnableScheduling
public class SchedulerConfig  implements SchedulingConfigurer {
    private final int POOL_SIZE = 10;
    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        ThreadPoolTaskScheduler threadPoolTaskScheduler = new ThreadPoolTaskScheduler();
        threadPoolTaskScheduler.setPoolSize(POOL_SIZE);
        threadPoolTaskScheduler.setThreadNamePrefix("scheduled-task-pool-");
        threadPoolTaskScheduler.initialize();
        taskRegistrar.setTaskScheduler(threadPoolTaskScheduler);
    }

    @Bean
    public PlannedOrderService plannedOrderService(PlannedOrderRepository plannedOrderRepository, OrderRepository orderRepository){
        return new PlannedOrderService(plannedOrderRepository, orderRepository);
    }
}
