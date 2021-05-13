package miu.edu.ecommerce.repository;

import miu.edu.ecommerce.domain.Order;
import org.springframework.data.repository.CrudRepository;
import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {

//    @Query(value = "UPDATE Order o SET o.currentStatus = 'Cancelled' WHERE o.id = :orderId")
//    public Order cancelOrder(long orderId);

        public List<Order> findAllByBuyerId(long buyerId);
        public Optional<Order> findById(Long Id);
        public  List<Order> findAll();

        public Order findOrderById(Long id);




}
