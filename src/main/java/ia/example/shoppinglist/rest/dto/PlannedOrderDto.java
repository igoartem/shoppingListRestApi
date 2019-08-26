package ia.example.shoppinglist.rest.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PlannedOrderDto extends OrderDto {
    private String planCron;
    private LocalDateTime planDate;
}
