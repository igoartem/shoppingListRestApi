package ia.example.shoppinglist.domain;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "entry_orders")
public class EntryOrder extends Entity {

    private Product product;
    private Integer price;
    private String description;
    private StateEntryOrder stateOrder = StateEntryOrder.NEW;

    public enum StateEntryOrder {
        NEW, DONE, CANCEL
    }

}
