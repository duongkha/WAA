package miu.edu.ecommerce.service;

import miu.edu.ecommerce.domain.Order;
import miu.edu.ecommerce.domain.OrderLine;
import miu.edu.ecommerce.repository.OrderLineRepository;
import miu.edu.ecommerce.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderLineRepository orderLineRepository;

    @Override
    public Optional<Order> getOrderById(long orderId){
        return orderRepository.findById(orderId);   //checked
    }

    @Override
    public Order cancelOrder(long orderId){
        return orderRepository.cancelOrder(orderId);
    }

    @Override
    public String getOrderStatus(long orderId){//checked
        return orderRepository.findById(orderId).get().getCurrentStatus();
        //return orderRepository.getOrderStatus(orderId);
    }

    @Override
    public Order createOrder(Order newOrder){
        return orderRepository.save(newOrder);
    } //checked

    @Override
    public List<OrderLine> getOrderForBuyer(long orderId){
        List<OrderLine> buyerOrderLine = new ArrayList<OrderLine>();
        buyerOrderLine = orderRepository.getOrderForBuyer(orderId);
        return buyerOrderLine;
    }

    @Override
    public List<OrderLine> getOrderLineById(long orderId){
        List<OrderLine> listOrderLine = new ArrayList<>();
        return orderLineRepository.getOrderLineById(orderId);
    }
}
