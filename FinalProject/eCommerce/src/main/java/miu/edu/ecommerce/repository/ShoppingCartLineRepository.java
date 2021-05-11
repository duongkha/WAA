package miu.edu.ecommerce.repository;

import miu.edu.ecommerce.domain.ShoppingCart;
import miu.edu.ecommerce.domain.ShoppingCartLine;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ShoppingCartLineRepository extends CrudRepository<ShoppingCartLine,Long> {
    public ShoppingCartLine save(ShoppingCartLine cartLine);
    public void deleteById(Long cartLineId);
}
