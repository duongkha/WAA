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
            Optional<ShoppingCartLine> oldCartLine =  foundCart.getCartLines().stream().filter(p->p.getProduct().getId()==cartline.getProduct().getId()).findFirst();

            //check if Product exists in cart
            if (oldCartLine.isPresent()) {
                ShoppingCartLine foundCartLine = oldCartLine.get();
                foundCartLine.setQuantity(foundCartLine.getQuantity() + cartline.getQuantity());
                foundCartLine.setLineTotal(foundCartLine.getLineTotal() + cartline.getQuantity()*foundCartLine.getPrice());
                shoppingCartLineRepository.save(foundCartLine);
            }
            else{
                foundCart.setTotalMoney(foundCart.getTotalMoney() + cartline.getLineTotal());
                foundCart.setTotalQuantity(foundCart.getTotalQuantity() + cartline.getQuantity());
                cartline.setCart(foundCart);
                shoppingCartRepository.save(foundCart);
                shoppingCartLineRepository.save(cartline);
            }
            calculateTotalInShoppingCart(cartId);
        }
    }

    private void calculateTotalInShoppingCart(Long cartId){
        Optional<ShoppingCart> cart =  shoppingCartRepository.findById(cartId);
        if(cart.isPresent()) {
            ShoppingCart foundCart = cart.get();
            Integer quantity = foundCart.getCartLines().stream().map(l -> l.getQuantity()).reduce(0, (a, b) -> a + b);
            Double money = foundCart.getCartLines().stream().map(l -> l.getLineTotal()).reduce(0.0, (a, b) -> a + b);
            foundCart.setTotalQuantity(quantity);
            foundCart.setTotalMoney(money);
            shoppingCartRepository.save(foundCart);
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
                foundCart.setTotalQuantity(foundCart.getTotalQuantity() - cartLine.get().getQuantity());
                shoppingCartRepository.save(foundCart);
                shoppingCartLineRepository.deleteById(cartLineId);
            }
            calculateTotalInShoppingCart(cartId);
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
            foundCart.setTotalQuantity(foundCart.getTotalQuantity() - foundCartLine.getQuantity() + newCartLine.getQuantity());
            newCartLine.setCart(foundCart);
            shoppingCartLineRepository.save(newCartLine);
            shoppingCartRepository.save(foundCart);
        }
        calculateTotalInShoppingCart(cartId);
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
            foundCart.setTotalQuantity(foundCart.getTotalQuantity() - foundCartLine.getQuantity() + newQuantity);

            shoppingCartLineRepository.save(foundCartLine);
            shoppingCartRepository.save(foundCart);
        }
        calculateTotalInShoppingCart(cartId);
    }

    @Override
    public List<ShoppingCart> getShoppingCartByBuyerNotCompleted(Long buyerId) {
        return shoppingCartRepository.getShoppingCartByBuyerNotCompleted(buyerId);

    }

}
