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

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private ShippingService shippingService;

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private ShoppingCartService shoppingCartService;


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

//    @PostMapping("/{orderId}")
//    public @ResponseBody OrderDTO cancelOrder(@PathVariable long orderId){
//        Optional<Order> orderOptional = orderService.getOrderById(orderId);
//        if(orderOptional.isPresent()){
//            return modelMapper.map(orderOptional.get(), OrderDTO.class);
//        }
//        return null;
//    }

    public String getOrderStatus(long orderId){
        return orderService.getOrderStatus(orderId);
    }

    public List<OrderLine> getOrderLineById(long orderId){
        return orderService.getOrderLineById(orderId);
    }


//    public List<OrderLine> getOrderForBuyer(long orderId);

    private OrderLine createOrderLineFromCartLine(ShoppingCartLine cartLine){
        OrderLine line = new OrderLine();
        line.setProduct(cartLine.getProduct());
        line.setPrice(cartLine.getPrice());
        line.setLineTotal(cartLine.getLineTotal());
        line.setQuantity(cartLine.getQuantity());
        return line;
    }
    public Order createOrderFromCart(Long cartId, Shipping shipping, Payment payment){
        Order order = new Order();
        Shipping shipping1 = shippingService.createShipping(shipping);
        Payment payment1 = paymentService.createPayment(payment);
        Optional<ShoppingCart> cart = shoppingCartService.getShoppingCart(cartId);
        if(cart.isPresent()){
            ShoppingCart cart1 = cart.get();
            order.setShipping(shipping1);
            order.setPayment(payment1);
            order.setTotalMoney(cart1.getTotalMoney());
            order.setBuyer(cart1.getBuyer());
            List<ShoppingCartLine> cartLines = shoppingCartService.getLinesByShoppingCart(cartId);
            cartLines.forEach(cartline -> {
                OrderLine orderLine = createOrderLineFromCartLine(cartline);
                orderLine.setOrder(order);
            });

            Order order1 = orderService.createOrder(order);
            return order1;
        }
        return null;
    }

}

