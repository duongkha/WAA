package miu.edu.ecommerce.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class OrderDTO {

    private long id;

    private LocalDate orderDate;

    private Double totalMoney;

    private String currentStatus;

    private BuyerDTO buyer;

    private PaymentDTO payment;

}
