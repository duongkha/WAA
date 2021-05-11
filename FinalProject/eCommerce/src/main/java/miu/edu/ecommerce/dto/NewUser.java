package miu.edu.ecommerce.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class NewUser extends UserDTO {
    private String password;
}
