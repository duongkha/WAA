package miu.edu.ecommerce.controller;

import miu.edu.ecommerce.domain.*;
import miu.edu.ecommerce.dto.ProductDTO;
import miu.edu.ecommerce.dto.ShoppingCartDTO;
import miu.edu.ecommerce.dto.ShoppingCartLineDTO;
import miu.edu.ecommerce.service.ShoppingCartService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping("/api/shoppingcarts")
public class ShoppingCartController {
    @Autowired
    private ShoppingCartService shoppingCartService;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    OrderController orderController;

    @PostMapping()
    public ShoppingCart createShoppingCart(@RequestBody ShoppingCart cart){
        return shoppingCartService.createShoppingCart(cart);
    }

    @GetMapping("/{cartId}")
    public ShoppingCartDTO getShoppingCart(@PathVariable Long cartId){
        Optional<ShoppingCart> cart = shoppingCartService.getShoppingCart(cartId);
        if(cart.isPresent()){
            return modelMapper.map(cart.get(), ShoppingCartDTO.class);
        }
        return null;
    }

    @GetMapping("/{cartId}/cartlines")
    public List<ShoppingCartLineDTO> getLinesFromShoppingCart(@PathVariable Long cartId){
        List<ShoppingCartLine> cartLines = shoppingCartService.getLinesByShoppingCart(cartId);
        return cartLines.stream()
                .map(line -> modelMapper.map(line, ShoppingCartLineDTO.class))
                .collect(Collectors.toList());
    }

    // add line to shopping cart
    @PostMapping("/{cartId}/cartlines")
    public ShoppingCartDTO addLineToShoppingCart(@PathVariable Long cartId, @RequestBody ShoppingCartLine cartLine){
        //check Product is exist in cartId
        shoppingCartService.addLineToShoppingCart(cartId, cartLine);
        return getShoppingCart(cartId);
    }
    // update line in shopping cart
    @PutMapping("/{cartId}/cartlines")
    public ShoppingCartDTO updateLineInShoppingCart(@PathVariable Long cartId, @RequestBody ShoppingCartLine cartLine){
        shoppingCartService.updateLineInShoppingCart(cartId, cartLine);
        return getShoppingCart(cartId);
    }

    // update quantity in shopping cart
    @PutMapping("/{cartId}/cartlines/{lineId}")
    public ShoppingCartDTO updateQuantityInShoppingCart(@PathVariable Long cartId, @PathVariable Long lineId, @RequestBody Integer newQuantity){
        shoppingCartService.updateQuantityInShoppingCartLine(cartId, lineId, newQuantity);
        return getShoppingCart(cartId);
    }

    // remove line from shopping cart
    @DeleteMapping("/{cartId}/cartlines/{cartLineId}")
    public ShoppingCartDTO removeLineFromShoppingCart(@PathVariable Long cartId, @PathVariable Long cartLineId){
        shoppingCartService.removeLineFromShoppingCart(cartId, cartLineId);
        return getShoppingCart(cartId);
    }


    @PostMapping("/{cartId}/createorder")
    public void createOrder(@PathVariable Long cartId, @RequestBody ShippingAndPayment shippingAndPayment) {
        orderController.createOrderFromCart(cartId, shippingAndPayment.shipping, shippingAndPayment.payment);
//        return orderService.createOrder(order);
    }   //checked

}

class ShippingAndPayment {
    public Shipping shipping;
    public Payment payment;
}
