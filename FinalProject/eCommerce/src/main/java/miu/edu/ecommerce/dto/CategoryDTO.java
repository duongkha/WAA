package miu.edu.ecommerce.dto;

import lombok.Getter;
import lombok.Setter;
import miu.edu.ecommerce.domain.Product;

import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class CategoryDTO {
    private long id;
    private String name;
    private List<ProductDTO> products = new ArrayList();
}
