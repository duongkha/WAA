package miu.edu.ecommerce.repository;

import miu.edu.ecommerce.domain.Category;
import miu.edu.ecommerce.domain.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends CrudRepository<Product,Long> {
    public List<Product> findAll();
    public Optional<Product> findById(long productId);
    public Product save(Product product);
    public void deleteById(long productId);
}
