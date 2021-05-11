package miu.edu.ecommerce.dto;

import miu.edu.ecommerce.domain.User;

import javax.persistence.*;

public class BuyerDTO {
    private long id;
    private int accumulatedPoints;
    UserDTO user;
}
