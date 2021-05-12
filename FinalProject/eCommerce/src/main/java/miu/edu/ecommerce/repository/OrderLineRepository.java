package miu.edu.ecommerce.repository;

import miu.edu.ecommerce.domain.OrderLine;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderLineRepository extends CrudRepository<OrderLineRepository,Long> {
    @Query("SELECT ol.o FROM OrderLine ol WHERE ol.id = :orderId")
    public List<OrderLine> getOrderLineById(@Param("orderId") long orderId);
}
