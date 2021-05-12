package miu.edu.ecommerce.service;

import miu.edu.ecommerce.domain.Payment;
import miu.edu.ecommerce.domain.Product;

public interface PaymentService {
    Payment createPayment(Payment payment);
}
