package miu.edu.ecommerce.dto;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class UserDTO {

    private Long id;

    private String username;
    private boolean enabled;

    private String firstName;

    private String lastName;

    private String phoneNumber;

    private String email;

    private String photo;

    private AddressDTO address;

    private Set<RoleDTO> roles = new HashSet<>();
}
