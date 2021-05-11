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
    public ShoppingCart addLineToShoppingCart(Long cartId, ShoppingCartLine cartline) {
        shoppingCartLineRepository.save(cartline);
        return shoppingCartRepository.findById(cartId).get();
    }

    @Override
    public ShoppingCart removeLineFromShoppingCart(Long cartId, Long cartLineId) {
        shoppingCartLineRepository.deleteById(cartLineId);
        return shoppingCartRepository.findById(cartId).get();
    }

    @Override
    public List<ShoppingCartLine> getLinesByShoppingCart(Long cartId) {
        return shoppingCartRepository.getLinesByShoppingCard(cartId);
    }

    @Override
    public Optional<ShoppingCart> getShoppingCart(Long cartId) {
        return shoppingCartRepository.findById(cartId);
    }

}
