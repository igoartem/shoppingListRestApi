package ia.example.shoppinglist.domain;

import java.io.Serializable;

import com.sun.xml.internal.bind.v2.model.core.ID;

public abstract class Entity implements Serializable {
    public abstract ID getId();

    public abstract void setId(ID id);
}
