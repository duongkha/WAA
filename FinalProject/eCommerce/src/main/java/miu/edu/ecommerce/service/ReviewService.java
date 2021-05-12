package miu.edu.ecommerce.service;

import miu.edu.ecommerce.domain.Product;
import miu.edu.ecommerce.domain.Review;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface ReviewService {
    void createReview(Review review);
    Optional<Review> getReviewById(Long reviewId);
    List<Review> getReviewsNotApproved();
    Boolean approveReview(Long reviewId);
}
