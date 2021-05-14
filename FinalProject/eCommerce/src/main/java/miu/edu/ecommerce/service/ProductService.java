package miu.edu.ecommerce.service;

import miu.edu.ecommerce.domain.Product;
import miu.edu.ecommerce.domain.Review;

import javax.print.attribute.standard.PrinterURI;
import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<Product> getAll();
    Optional<Product> getProductById(Long productId);
    Boolean createProduct(Product product, Long userId);
    void deleteProduct(Long productId);
    List<Review> getApprovedReviewsByProductId(Long productId);
    void updateProduct(Product product);
}
