package ia.example.shoppinglist.domain;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * Списки для плановых покупок
 * На основе их создаются Order в основной список
 */
@Data
public class PlannedOrder extends Order {
    private String planCron;
    private LocalDateTime planDate;

}
