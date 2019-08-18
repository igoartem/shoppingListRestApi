package ia.example.shoppinglist.domain;

import java.io.Serializable;

import lombok.Data;

@Data
public abstract class Entity implements Serializable {

    private String id;
}
