package ia.example.shoppinglist.domain;

import java.io.Serializable;

public abstract class Entity implements Serializable {
    public abstract String getId();

    public abstract void setId(String id);
}
