package miu.edu.ecommerce.dto;

import lombok.Getter;
import lombok.Setter;
import miu.edu.ecommerce.domain.PaymentMethod;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDate;

@Getter
@Setter
public class PaymentDTO {
    private long id;

    private LocalDate paymentDate;

    private Double paymentAmount;

    private String cardHolder;

    private PaymentMethodDTO paymentMethod;
}
