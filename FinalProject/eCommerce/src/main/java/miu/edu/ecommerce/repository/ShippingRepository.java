package miu.edu.ecommerce.repository;

import miu.edu.ecommerce.domain.Shipping;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShippingRepository extends CrudRepository<Shipping, Long> {
     Shipping save(Shipping shipping);
}
