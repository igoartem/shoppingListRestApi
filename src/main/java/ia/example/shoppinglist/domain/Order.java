package ia.example.shoppinglist.domain;

import lombok.Data;

@Data
public class Order extends Entity {

    private String id;
    private Product product;
    private Integer price;
    private String description;
    private StateOrder stateOrder = StateOrder.NEW;

    public enum StateOrder {
        NEW, DONE, CANCEL
    }

}
