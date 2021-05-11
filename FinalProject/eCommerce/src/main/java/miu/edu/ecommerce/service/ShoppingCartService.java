package miu.edu.ecommerce.service;

import miu.edu.ecommerce.domain.ShoppingCart;
import miu.edu.ecommerce.domain.ShoppingCartLine;

import java.util.List;
import java.util.Optional;

public interface ShoppingCartService {
    ShoppingCart createShoppingCart(ShoppingCart cart);
    void addLineToShoppingCart(Long cartId, ShoppingCartLine cartLine);
    void removeLineFromShoppingCart(Long cartId, Long cartLineId);
    List<ShoppingCartLine> getLinesByShoppingCart(Long cartId);
    Optional<ShoppingCart> getShoppingCart(Long cartId);
}
