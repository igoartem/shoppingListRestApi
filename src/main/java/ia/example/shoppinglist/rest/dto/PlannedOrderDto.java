package ia.example.shoppinglist.rest.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PlannedOrderDto extends EntryOrderDto {
    private String planCron;
    private LocalDateTime planDate;
    private OrderDto orderDto;
}
