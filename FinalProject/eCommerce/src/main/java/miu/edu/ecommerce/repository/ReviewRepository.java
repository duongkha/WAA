package miu.edu.ecommerce.repository;


import miu.edu.ecommerce.domain.Review;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReviewRepository extends CrudRepository<Review,Long> {
    Optional<Review> findById(long reviewId);
    Review save(Review review);

    @Query(value = "SELECT r FROM Review r WHERE r.approved <> 1")
    List<Review> getReviewsNotApproved();

}
