package miu.edu.ecommerce.repository;

import miu.edu.ecommerce.domain.Payment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends CrudRepository<Payment, Long> {
    Payment save(Payment payment);
}
