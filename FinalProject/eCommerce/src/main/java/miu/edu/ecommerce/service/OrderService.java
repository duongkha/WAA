package miu.edu.ecommerce.service;

import miu.edu.ecommerce.domain.Order;
import miu.edu.ecommerce.domain.OrderLine;
import miu.edu.ecommerce.domain.Payment;
import miu.edu.ecommerce.domain.Shipping;

import java.util.List;
import java.util.Optional;


public interface OrderService {

    public Optional<Order> getOrderById(long orderId);

//    public Order cancelOrder(long id);

    public String getOrderStatus(long orderId);

    public Order createOrder(Order newOrder);

    List<Order> getOrderForBuyer(long buyerId);
    List<Order> getAll();
    public List<OrderLine> getOrderLineById(long orderId);

    List<Order> getOrderBySellerId(long sellerId);

    Boolean cancelOrder(long orderId);
    Boolean shippedOrder(long orderId);
    Boolean deliveredOrder(long orderId);

    void createOrderFromCart(Long cartId, Shipping shipping, Payment payment);
}
