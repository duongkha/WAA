package miu.edu.ecommerce.repository;

import miu.edu.ecommerce.domain.Product;
import miu.edu.ecommerce.domain.Review;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReviewRepository extends CrudRepository<Review,Long> {
    public Optional<Review> findById(long reviewId);
    public Review save(Review review);
    public void deleteById(long reviewId);
}
