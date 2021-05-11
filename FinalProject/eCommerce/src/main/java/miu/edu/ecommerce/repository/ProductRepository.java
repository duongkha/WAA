package miu.edu.ecommerce.repository;


import miu.edu.ecommerce.domain.Product;
import miu.edu.ecommerce.domain.Review;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends CrudRepository<Product,Long> {
    public List<Product> findAll();
    public Optional<Product> findById(Long productId);
    public Product save(Product product);
    public void deleteById(Long productId);

    @Query(value = "SELECT p.reviews FROM Product p WHERE p.id = :productId")
    public List<Review> findReviewsByProductId(@Param("productId") Long productId);

}
