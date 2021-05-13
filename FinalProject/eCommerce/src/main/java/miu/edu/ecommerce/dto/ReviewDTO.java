package miu.edu.ecommerce.dto;

import lombok.Getter;
import lombok.Setter;
import miu.edu.ecommerce.domain.User;

import java.time.LocalDate;

@Getter
@Setter
public class ReviewDTO {

    private long id;
    private LocalDate date;
    private String content;
    private int numberOfStars;
    private boolean approved;

    private UserDTO user;
//    private ProductDTO product;
}
