package miu.edu.ecommerce.repository;

import miu.edu.ecommerce.domain.Order;
import miu.edu.ecommerce.domain.OrderLine;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

import javax.persistence.Column;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {

//    @Query(value = "UPDATE Order o SET o.currentStatus = 'Cancelled' WHERE o.id = :orderId")
//    public Order cancelOrder(long orderId);

//    public List<OrderLine> getOrderForBuyer(long orderId);
        public  List<Order> findAll();

        public Order findOrderById(Long id);




}
