package miu.edu.ecommerce.controller;

import miu.edu.ecommerce.domain.ShoppingCart;
import miu.edu.ecommerce.domain.ShoppingCartLine;
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

@RestController
@RequestMapping("/shoppingcarts")
public class ShoppingCartController {
    @Autowired
    private ShoppingCartService shoppingCartService;

    @Autowired
    ModelMapper modelMapper;

    @PostMapping()
    public ShoppingCart createShoppingCart(@RequestBody ShoppingCart cart){
        return shoppingCartService.createShoppingCart(cart);
    }

    @GetMapping("{cartId}")
    public ShoppingCartDTO getShoppingCart(@PathVariable Long cartId){
        Optional<ShoppingCart> cart = shoppingCartService.getShoppingCart(cartId);
        if(cart.isPresent()){
            return modelMapper.map(cart.get(), ShoppingCartDTO.class);
        }
        return null;
    }


    @GetMapping("{cartId}/cartlines")
    public List<ShoppingCartLineDTO> getLinesFromShoppingCart(@PathVariable Long cartId){
        List<ShoppingCartLine> cartLines = shoppingCartService.getLinesByShoppingCart(cartId);
        return cartLines.stream()
                .map(line -> modelMapper.map(line, ShoppingCartLineDTO.class))
                .collect(Collectors.toList());
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
