package ia.example.shoppinglist.rest.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class UserDto extends EntityDto {
    private String fio;
    private String login;
    private String password;
    private String about;
}
