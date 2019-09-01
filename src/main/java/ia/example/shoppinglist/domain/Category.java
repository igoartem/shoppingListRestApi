package ia.example.shoppinglist.domain;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "categories")
public class Category extends Entity {
    private String name;
}
