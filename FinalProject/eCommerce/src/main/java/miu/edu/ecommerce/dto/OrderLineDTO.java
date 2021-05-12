package miu.edu.ecommerce.dto;

import lombok.Getter;
import lombok.Setter;
import miu.edu.ecommerce.domain.Order;
import miu.edu.ecommerce.domain.Product;

import javax.persistence.*;

@Getter
@Setter
public class OrderLineDTO {
    private long id;
    private int quantity;
    private Double price;
    private Double lineTotal;
    ProductDTO product;
}
