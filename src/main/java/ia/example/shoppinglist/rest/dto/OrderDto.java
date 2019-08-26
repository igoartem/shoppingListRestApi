package ia.example.shoppinglist.rest.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import ia.example.shoppinglist.domain.EntryOrder;
import ia.example.shoppinglist.domain.User;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class OrderDto extends EntityDto {

    private String name;
    private LocalDateTime reminderDate;
    private LocalDateTime createDate;
    private String userId;
    private Boolean actual;
    private List<EntryOrderDto> entryOrders;
}
