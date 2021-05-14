package miu.edu.ecommerce.dto;

import lombok.Getter;
import lombok.Setter;
import miu.edu.ecommerce.domain.Category;

import javax.persistence.Column;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ProductDTO {

    private long id;
    private String productName;

    private String producer;
    private String description;
    private String color;
    private String size;
    private Double price;
    private Double rating;
    private String photo;
    private Integer numReviews;
    private LocalDate dueDate;
    private int quantityInStock;
    private CategoryDTO category;
    //private SellerDTO seller;
//    private List<ReviewDTO> reviews = new ArrayList();

}
