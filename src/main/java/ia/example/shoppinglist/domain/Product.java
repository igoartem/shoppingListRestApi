package ia.example.shoppinglist.domain;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "products")
public class Product extends Entity {
    private String name;
    private String description;
    private String price;
    private Category category;
    private Manufacturer manufacturer;
}
