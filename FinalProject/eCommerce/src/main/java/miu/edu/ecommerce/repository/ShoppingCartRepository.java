package miu.edu.ecommerce.repository;

import miu.edu.ecommerce.domain.Product;
import miu.edu.ecommerce.domain.ShoppingCart;
import miu.edu.ecommerce.domain.ShoppingCartLine;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface ShoppingCartRepository extends CrudRepository<ShoppingCart,Long> {
    public ShoppingCart save(ShoppingCart shoppingCart);
    public Optional<ShoppingCart> findById(Long cartId);

    @Query(value = "SELECT sh.cartLines FROM ShoppingCart sh WHERE sh.id = :cartId")
    public List<ShoppingCartLine> getLinesByShoppingCard(@Param("cartId") Long cartId);



//    List<ShoppingCart> shoppingCarts = new ArrayList<>();
//    public void createShoppingCart(ShoppingCart cart){
//        shoppingCarts.add(cart);
//
//    }
//
//    public void addLineToShoppingCart(Long cartId, ShoppingCartLine cartLine){
//       Optional<ShoppingCart> foundCart = shoppingCarts.stream()
//                .filter(c -> c.getId() == cartId)
//               .findFirst();
//       if(foundCart.isPresent()){
//           foundCart.get().getCartLines().add(cartLine);
//       }
//    }
//
//    public void removeLineFromShoppingCart(Long cartId, Long cartLineId){
//        Optional<ShoppingCart> foundCart = shoppingCarts.stream()
//                .filter(c -> c.getId() == cartId)
//                .findFirst();
//        if(foundCart.isPresent()){
//            Optional<ShoppingCartLine> foundCartLine = foundCart.get().getCartLines()
//                    .stream()
//                    .filter(line -> line.getId() == cartLineId).findFirst();
//            if(foundCartLine.isPresent()){
//                foundCart.get().getCartLines().remove(foundCartLine.get());
//            }
//        }
//    }
}
