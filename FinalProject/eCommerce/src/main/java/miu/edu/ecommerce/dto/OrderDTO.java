package miu.edu.ecommerce.dto;

import lombok.Getter;
import lombok.Setter;
import miu.edu.ecommerce.domain.Buyer;
import miu.edu.ecommerce.domain.OrderLine;
import miu.edu.ecommerce.domain.Payment;
import miu.edu.ecommerce.domain.Shipping;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class OrderDTO {

    private long id;

    private LocalDate orderDate;
    private Integer totalQuantity;
    private Double totalMoney;
    private String currentStatus;
    private BuyerDTO buyer;
    private PaymentDTO payment;
    private ShippingDTO shipping;
    private List<OrderLineDTO> cartLines = new ArrayList();

}
