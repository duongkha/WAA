package miu.edu.ecommerce.repository;

import miu.edu.ecommerce.domain.Product;
import miu.edu.ecommerce.domain.ShoppingCart;
import miu.edu.ecommerce.domain.ShoppingCartLine;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;


@Repository
public interface ShoppingCartRepository extends CrudRepository<ShoppingCart,Long> {
    public ShoppingCart save(ShoppingCart shoppingCart);
    public Optional<ShoppingCart> findById(Long cartId);

    @Query(value = "SELECT sh.cartLines FROM ShoppingCart sh WHERE sh.id = :cartId")
    public List<ShoppingCartLine> getLinesByShoppingCard(@Param("cartId") Long cartId);

    @Query(value = "SELECT sh FROM ShoppingCart sh WHERE sh.buyer.id = :buyerId and sh.completed <> 1")
    public Optional<ShoppingCart> getShoppingCartByBuyerNotCompleted(@Param("buyerId") Long cartId);
}
