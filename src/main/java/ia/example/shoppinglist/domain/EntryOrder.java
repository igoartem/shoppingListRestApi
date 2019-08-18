package ia.example.shoppinglist.domain;

import lombok.Data;

@Data
public class EntryOrder extends Entity {

    private Product product;
    private Integer price;
    private String description;
    private StateEntryOrder stateOrder = StateEntryOrder.NEW;

    public enum StateEntryOrder {
        NEW, DONE, CANCEL
    }

}
