package ia.example.shoppinglist.domain;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "manufacturer")
public class Manufacturer extends Entity {
    private String name;
}
