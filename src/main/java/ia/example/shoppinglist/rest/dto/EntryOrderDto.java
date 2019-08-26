package ia.example.shoppinglist.rest.dto;

import ia.example.shoppinglist.domain.EntryOrder;
import lombok.Data;

@Data
public class EntryOrderDto extends EntityDto {
    private ProductDto product;
    private Integer price;
    private String description;
    private EntryOrder.StateEntryOrder stateOrder = EntryOrder.StateEntryOrder.NEW;
}
