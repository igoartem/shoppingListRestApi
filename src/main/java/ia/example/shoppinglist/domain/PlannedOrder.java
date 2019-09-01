package ia.example.shoppinglist.domain;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

/**
 * Списки для плановых покупок
 * На основе их создаются Order в основной список
 */
@Data
@Document(collection = "planned_orders")
public class PlannedOrder extends Entity{
    private String planCron;
    private LocalDateTime planDate;
    private Order order;
    private String userId;
}
