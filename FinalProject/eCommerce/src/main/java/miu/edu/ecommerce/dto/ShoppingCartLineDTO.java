package miu.edu.ecommerce.dto;

import lombok.Getter;
import lombok.Setter;
import miu.edu.ecommerce.domain.Product;

@Getter
@Setter
public class ShoppingCartLineDTO {

    private long id;
    private int quantity;
    private Double price;
    private Double lineTotal;
    ProductDTO product;
}
