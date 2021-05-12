package miu.edu.ecommerce.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
public class PaymentMethodDTO {
    private long id;

    private String paymentMethod;
}
