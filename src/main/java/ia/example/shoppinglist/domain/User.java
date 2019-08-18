package ia.example.shoppinglist.domain;

import lombok.Data;

@Data
public class User extends Entity {

    private String id;
    private String fio;
    private String login;
    private String password;
    private String about;
    private Role role = Role.CLIENT;

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    public enum Role {
        CLIENT, ADMIN
    }
}
