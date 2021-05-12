package miu.edu.ecommerce.controller;

import miu.edu.ecommerce.domain.*;
import miu.edu.ecommerce.dto.OrderDTO;
import miu.edu.ecommerce.service.OrderService;
import miu.edu.ecommerce.service.PaymentService;
import miu.edu.ecommerce.service.ShippingService;
import miu.edu.ecommerce.service.ShoppingCartService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    ModelMapper modelMapper;

    @GetMapping("/{orderId}")
    public @ResponseBody OrderDTO getOrderById(@PathVariable long orderId){ //checked
        Optional<Order> orderOptional = orderService.getOrderById(orderId);
        if(orderOptional.isPresent()){
            return modelMapper.map(orderOptional.get(), OrderDTO.class);
        }
        return null;
    }

//    @PostMapping()
//    public Order createOrder(@RequestBody Order order) {    //checked
//       return orderService.createOrder(order);
//    }   //checked


    @GetMapping("/{orderId}/cancel")
    public @ResponseBody Boolean cancelOrder(@PathVariable long orderId){
        Optional<Order> orderOptional = orderService.getOrderById(orderId);
        if(orderOptional.isPresent()){
            orderOptional.get().setCurrentStatus("CANCELLED");
            return true;
        }
        return false;
    }

    public String getOrderStatus(long orderId){
        return orderService.getOrderStatus(orderId);
    }

    public List<OrderLine> getOrderLineById(long orderId){
        return orderService.getOrderLineById(orderId);
    }


//    public List<OrderLine> getOrderForBuyer(long orderId);

    public void createOrderFromCart(Long cartId, Shipping shipping, Payment payment){
        orderService.createOrderFromCart(cartId,shipping,payment);
    }

}

