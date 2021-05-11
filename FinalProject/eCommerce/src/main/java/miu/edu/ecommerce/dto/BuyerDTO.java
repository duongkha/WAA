package miu.edu.ecommerce.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BuyerDTO {
    private long id;
    private int accumulatedPoints;
    UserDTO user;
}
