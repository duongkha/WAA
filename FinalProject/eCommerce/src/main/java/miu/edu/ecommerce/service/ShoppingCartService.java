package miu.edu.ecommerce.service;

import miu.edu.ecommerce.domain.ShoppingCart;
import miu.edu.ecommerce.domain.ShoppingCartLine;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

public interface ShoppingCartService {
    ShoppingCart createShoppingCart(ShoppingCart cart);
    void addLineToShoppingCart(Long cartId, ShoppingCartLine cartLine);
    void removeLineFromShoppingCart(Long cartId, Long cartLineId);
    List<ShoppingCartLine> getLinesByShoppingCart(Long cartId);
    Optional<ShoppingCart> getShoppingCart(Long cartId);
    void updateLineInShoppingCart(Long cartId, ShoppingCartLine newCartLine);
    void updateQuantityInShoppingCartLine(Long cartId,Long lineId,Integer newQuantity);
    Optional<ShoppingCart> getShoppingCartByBuyerNotCompleted(Long buyerId);
}
