package ia.example.shoppinglist.domain;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "orders")
public class Order extends Entity {
    private String name;
    private LocalDateTime reminderDate;
    private LocalDateTime createDate;
    private List<EntryOrder> entryOrders;
    private String userId;
    private Boolean actual;
}
