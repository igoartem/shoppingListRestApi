package ia.example.shoppinglist.domain;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;
import lombok.Setter;

@Data
public class Order extends Entity {
    private String name;
    private LocalDateTime reminderDate;
    private LocalDateTime createDate;
    private List<EntryOrder> entryOrders;
    private User user;
    private Boolean actual;
}
