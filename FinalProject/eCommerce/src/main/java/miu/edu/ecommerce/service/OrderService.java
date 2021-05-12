package miu.edu.ecommerce.service;

import miu.edu.ecommerce.domain.Order;
import miu.edu.ecommerce.domain.OrderLine;

import java.util.List;
import java.util.Optional;


public interface OrderService {

    public Optional<Order> getOrderById(long orderId);

//    public Order cancelOrder(long id);

    public String getOrderStatus(long orderId);

    public Order createOrder(Order newOrder);

//    public List<OrderLine> getOrderForBuyer(long orderId);
    List<Order> getAll();
    public List<OrderLine> getOrderLineById(long orderId);

    List<Order> getOrderBySellerId(long sellerId);

    Boolean cancelOrder(long orderId);
    Boolean shippedOrder(long orderId);
    Boolean deliveredOrder(long orderId);

    }
