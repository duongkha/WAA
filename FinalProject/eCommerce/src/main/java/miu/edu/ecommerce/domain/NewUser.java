package miu.edu.ecommerce.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class NewUser {
    private Long id;

    private String username;
    private String password;
    private String email;
    private String phone;
    private String firstName;
    private String lastName;
    private Long role;
}
