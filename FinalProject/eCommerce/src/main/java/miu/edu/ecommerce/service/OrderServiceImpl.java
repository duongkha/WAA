package miu.edu.ecommerce.service;

import miu.edu.ecommerce.domain.Order;
import miu.edu.ecommerce.domain.OrderLine;
import miu.edu.ecommerce.repository.OrderLineRepository;
import miu.edu.ecommerce.repository.OrderRepository;
import org.modelmapper.internal.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

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

//    @Override
//    public Order cancelOrder(long orderId){
//        return orderRepository.cancelOrder(orderId);
//    }

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
    public List<Order> getAll() {
        return orderRepository.findAll();
    }

//    @Override
//    public List<OrderLine> getOrderForBuyer(long orderId){
//        List<OrderLine> buyerOrderLine = new ArrayList<OrderLine>();
//        buyerOrderLine = orderRepository.getOrderForBuyer(orderId);
//        return buyerOrderLine;
//    }

    @Override
    public List<OrderLine> getOrderLineById(long orderId){
        List<OrderLine> listOrderLine = new ArrayList<>();
        return orderLineRepository.getOrderLineById(orderId);
    }

    @Override
    public List<Order> getOrderBySellerId(long sellerId) {

        List<OrderLine> lines = orderLineRepository.findAll().stream().filter(ol->ol.getProduct().getSeller().getId() == sellerId).collect(Collectors.toList());

        List<Long> ids = lines.stream().map( l->l.getId()).collect(Collectors.toList());
        List<Order> orders = orderRepository.findAll().stream().filter(o->ids.contains(o.getId())).collect(Collectors.toList());
        return orders;
    }

    @Override
    public Boolean cancelOrder(long orderId) {
        Order order = orderRepository.findOrderById(orderId);
        if(order != null)
        {
            order.setCurrentStatus("CANCELLED");
            orderRepository.save(order);
            return true;
        }
        return false;
    }

    @Override
    public Boolean shippedOrder(long orderId) {
        Order order = orderRepository.findOrderById(orderId);
        if(order != null)
        {
            order.setCurrentStatus("SHIPPED");
            orderRepository.save(order);
            return true;
        }
        return false;
    }

    @Override
    public Boolean deliveredOrder(long orderId) {
        Order order = orderRepository.findOrderById(orderId);
        if(order != null)
        {
            order.setCurrentStatus("DELIVERED");
            orderRepository.save(order);
            return true;
        }
        return false;
    }
}
