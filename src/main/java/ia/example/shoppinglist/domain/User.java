package ia.example.shoppinglist.domain;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "users")
public class User extends Entity {

    private String fio;
    private String login;
    private String password;
    private String about;
}
