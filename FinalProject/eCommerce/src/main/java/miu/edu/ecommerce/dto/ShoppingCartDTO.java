package miu.edu.ecommerce.dto;

import lombok.Getter;
import lombok.Setter;
import miu.edu.ecommerce.domain.Buyer;
import miu.edu.ecommerce.domain.ShoppingCartLine;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ShoppingCartDTO {

    private long id;
    private LocalDate cartDate;
    private Double totalMoney;
    private boolean completed;
    private List<ShoppingCartLineDTO> cartLines = new ArrayList();
//    Buyer buyer;
}
