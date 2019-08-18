package ia.example.shoppinglist.domain;

import lombok.Data;

@Data
public class User extends Entity {

    private String fio;
    private String login;
    private String password;
    private String about;
}
