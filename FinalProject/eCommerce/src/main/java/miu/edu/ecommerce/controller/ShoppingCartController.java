package miu.edu.ecommerce.controller;

import miu.edu.ecommerce.domain.ShoppingCart;
import miu.edu.ecommerce.domain.ShoppingCartLine;
import miu.edu.ecommerce.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/shoppingcarts")
public class ShoppingCartController {
    @Autowired
    private ShoppingCartService shoppingCartService;

    @PostMapping()
    public ShoppingCart createShoppingCart(@RequestBody ShoppingCart cart){
        return shoppingCartService.createShoppingCart(cart);
    }

    @GetMapping("{cartId}")
    public void getShoppingCart(@PathVariable Long cartId){
        shoppingCartService.getShoppingCart(cartId);
    }

    @GetMapping("{cartId}/cartlines")
    public void getLinesFromShoppingCart(@PathVariable Long cartId){
        shoppingCartService.getLinesByShoppingCart(cartId);
    }


    @PostMapping("{cartId}/cartlines")
    public void addLineToShoppingCart(@PathVariable Long cartId, @RequestBody ShoppingCartLine cartLine){
        shoppingCartService.addLineToShoppingCart(cartId, cartLine);
    }

    @DeleteMapping("{cartId}/cartlines/{cartLineId}")
    public void removeLineToShoppingCart(@PathVariable Long cartId, @PathVariable Long cartLineId){
        shoppingCartService.removeLineFromShoppingCart(cartId, cartLineId);
    }
}
