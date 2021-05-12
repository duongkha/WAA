package miu.edu.ecommerce.service;

import miu.edu.ecommerce.domain.ShoppingCart;
import miu.edu.ecommerce.domain.ShoppingCartLine;
import miu.edu.ecommerce.repository.ShoppingCartLineRepository;
import miu.edu.ecommerce.repository.ShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService{
    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    @Autowired
    private ShoppingCartLineRepository shoppingCartLineRepository;

    @Override
    public ShoppingCart createShoppingCart(ShoppingCart cart) {
        return shoppingCartRepository.save(cart);
    }

    @Override
    public void addLineToShoppingCart(Long cartId, ShoppingCartLine cartline) {
        Optional<ShoppingCart> cart =  shoppingCartRepository.findById(cartId);
        if(cart.isPresent()){
            ShoppingCart foundCart = cart.get();
            foundCart.setTotalMoney(foundCart.getTotalMoney() + cartline.getLineTotal());
            cartline.setCart(foundCart);
            shoppingCartRepository.save(foundCart);
            shoppingCartLineRepository.save(cartline);
        }
    }

    @Override
    public void removeLineFromShoppingCart(Long cartId, Long cartLineId) {
        Optional<ShoppingCart> cart =  shoppingCartRepository.findById(cartId);
        if(cart.isPresent()){
            ShoppingCart foundCart = cart.get();
            Optional<ShoppingCartLine> cartLine = foundCart.getCartLines().stream()
                    .filter(line-> line.getId() == cartLineId)
                    .findFirst();
            if(cartLine.isPresent()){
                foundCart.setTotalMoney(foundCart.getTotalMoney() - cartLine.get().getLineTotal());
                shoppingCartRepository.save(foundCart);
                shoppingCartLineRepository.deleteById(cartLineId);
            }
        }
    }

    @Override
    public List<ShoppingCartLine> getLinesByShoppingCart(Long cartId) {
        return shoppingCartRepository.getLinesByShoppingCard(cartId);
    }

    @Override
    public Optional<ShoppingCart> getShoppingCart(Long cartId) {
        return shoppingCartRepository.findById(cartId);
    }

    @Override
    public void updateLineInShoppingCart(Long cartId, ShoppingCartLine newCartLine) {
        Optional<ShoppingCart> cart =  shoppingCartRepository.findById(cartId);
        Optional<ShoppingCartLine> cartLine =  shoppingCartLineRepository.findById(newCartLine.getId());
        if(cart.isPresent() && cartLine.isPresent()){
            ShoppingCart foundCart = cart.get();
            ShoppingCartLine foundCartLine = cartLine.get();
            foundCart.setTotalMoney(foundCart.getTotalMoney() - foundCartLine.getLineTotal() + newCartLine.getLineTotal());
            newCartLine.setCart(foundCart);
            shoppingCartLineRepository.save(newCartLine);
            shoppingCartRepository.save(foundCart);
        }
    }

    @Override
    public void updateQuantityInShoppingCartLine(Long cartId, Long lineId, Integer newQuantity) {
        Optional<ShoppingCart> cart =  shoppingCartRepository.findById(cartId);
        Optional<ShoppingCartLine> cartLine =  shoppingCartLineRepository.findById(lineId);
        if(cart.isPresent() && cartLine.isPresent()){
            ShoppingCart foundCart = cart.get();
            ShoppingCartLine foundCartLine = cartLine.get();
            Double oldLineTotal =  foundCartLine.getLineTotal();
            Double newLineTotal = foundCartLine.getPrice()*newQuantity;

            foundCartLine.setQuantity(newQuantity);
            foundCartLine.setLineTotal(newLineTotal);
            foundCart.setTotalMoney(foundCart.getTotalMoney() - oldLineTotal + newLineTotal);

            shoppingCartLineRepository.save(foundCartLine);
            shoppingCartRepository.save(foundCart);
        }
    }

}
