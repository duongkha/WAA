package miu.edu.ecommerce.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SellerDTO {
    private long id;

    private boolean approved;

    private String companyName;

    UserDTO user;
}
