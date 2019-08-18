package ia.example.shoppinglist.domain;

import lombok.Data;

@Data
public class Product extends Entity {

    private String id;
    private String name;
    private String description;
    private String price;
    private Category category;
    private Manufacturer manufacturer;
}
