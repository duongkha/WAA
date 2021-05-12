package miu.edu.ecommerce.repository;

import miu.edu.ecommerce.domain.OrderLine;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderLineRepository extends CrudRepository<OrderLine,Long> {
    @Query("SELECT ol.o FROM OrderLine ol WHERE ol.id = :orderId")
    public List<OrderLine> getOrderLineById(@Param("orderId") long orderId);
}
