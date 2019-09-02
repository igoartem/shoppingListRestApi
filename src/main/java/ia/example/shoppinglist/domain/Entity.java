package ia.example.shoppinglist.domain;

import java.io.Serializable;

import org.springframework.data.annotation.Id;

import lombok.Data;

@Data
public abstract class Entity implements Serializable {

    @Id
    private String id;
}
