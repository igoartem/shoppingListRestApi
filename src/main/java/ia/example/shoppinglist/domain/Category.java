package ia.example.shoppinglist.domain;

import lombok.Data;

@Data
public class Category extends Entity {

    private String id;
    private String name;
}
