package miu.edu.ecommerce.controller;

import miu.edu.ecommerce.domain.Order;
import miu.edu.ecommerce.domain.OrderLine;
import miu.edu.ecommerce.domain.Product;
import miu.edu.ecommerce.domain.Seller;
import miu.edu.ecommerce.dto.OrderDTO;
import miu.edu.ecommerce.dto.ProductDTO;
import miu.edu.ecommerce.dto.SellerDTO;
import miu.edu.ecommerce.service.OrderService;
import miu.edu.ecommerce.service.SellerService;
import miu.edu.ecommerce.service.SellerServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping("/api/sellers")
public class SellerController {
    @Autowired
    SellerService sellerService;
    @Autowired
    OrderService orderService;

    @Autowired
    ModelMapper modelMapper;

    @GetMapping
    public List<SellerDTO> getAll(){

        List<Seller> sellers = sellerService.getAll();
        return sellers.stream().map(s->modelMapper.map(s, SellerDTO.class)).collect(Collectors.toList());
    }

    @GetMapping("/notapproved")
    public List<SellerDTO> getNotApprovedSellers(){
        List<Seller> sellers = sellerService.getAll();
        return sellers.stream().filter(s->!s.isApproved()).map(s->modelMapper.map(s, SellerDTO.class)).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public SellerDTO getSellerById(@PathVariable("id") Long id){
        Seller seller = sellerService.getSellerByID(id);
        return modelMapper.map(seller, SellerDTO.class);
    }
    //Get products by seller
    @GetMapping("/{id}/products")
    public List<ProductDTO> getProductsBySellerId(@PathVariable("id") Long id){
        List<Product> products = sellerService.getProductsBySellerId(id);
        return products.stream()
                .map(p -> modelMapper.map(p, ProductDTO.class))
                .collect(Collectors.toList());
    }
    @GetMapping("/{id}/orders")
    public List<OrderDTO> getOrdersBySellerId(@PathVariable("id") Long id){
        List<Order> orders = orderService.getOrderBySellerId(id);
        return orders.stream()
                .map(o -> modelMapper.map(o, OrderDTO.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/{orderId}/cancel")
    public @ResponseBody Boolean cancelOrder(@PathVariable long orderId){
        return orderService.cancelOrder(orderId);
    }
    @GetMapping("/{orderId}/shipped")
    public @ResponseBody Boolean shippedOrder(@PathVariable long orderId){
        return orderService.shippedOrder(orderId);
    }
    @GetMapping("/{orderId}/delivered")
    public @ResponseBody Boolean deliveredOrder(@PathVariable long orderId){
        return orderService.deliveredOrder(orderId);
    }

}
