package ia.example.shoppinglist.domain;

import java.util.UUID;

import lombok.Data;
import lombok.EqualsAndHashCode;

import org.springframework.data.mongodb.core.mapping.Document;

@Data
@EqualsAndHashCode(callSuper = true)
public class EntryOrder extends Entity {

    private Product product;
    private Integer price;
    private String description;
    private StateEntryOrder stateOrder = StateEntryOrder.NEW;

    public enum StateEntryOrder {
        NEW, DONE, CANCEL
    }

    public EntryOrder() {
        super();
        this.setId(UUID.randomUUID().toString());
    }
}
