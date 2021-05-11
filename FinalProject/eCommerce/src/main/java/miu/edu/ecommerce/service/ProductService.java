package miu.edu.ecommerce.service;

import miu.edu.ecommerce.domain.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<Product> getAll();
    Optional<Product> getProductById(long productId);
    public void createProduct(Product product);
    public void deleteProduct(long productId);
}
