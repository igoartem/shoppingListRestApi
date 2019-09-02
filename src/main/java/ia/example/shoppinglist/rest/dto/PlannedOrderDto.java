package ia.example.shoppinglist.rest.dto;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class PlannedOrderDto extends EntityDto {
    private String planCron;
    private LocalDateTime planDate;
    private OrderDto orderDto;
    private String userId;
}
