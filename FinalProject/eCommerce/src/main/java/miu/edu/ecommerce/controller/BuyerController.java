package miu.edu.ecommerce.controller;

import miu.edu.ecommerce.domain.ShoppingCart;
import miu.edu.ecommerce.dto.ShoppingCartDTO;
import miu.edu.ecommerce.service.ShoppingCartService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api/buyers")
public class BuyerController {

    @Autowired
    private ShoppingCartService shoppingCartService;

    @Autowired
    ModelMapper modelMapper;

    @GetMapping("/{buyerId}/cartnotcompleted")
    public ShoppingCartDTO getShoppingCartByBuyerNotCompleted(@PathVariable Long buyerId){
        List<ShoppingCart> cart = shoppingCartService.getShoppingCartByBuyerNotCompleted(buyerId);
        if(cart.size()>0){
            return modelMapper.map(cart.get(0), ShoppingCartDTO.class);
        }
        return null;
    }


}
