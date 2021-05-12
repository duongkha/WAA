package miu.edu.ecommerce.service;

import miu.edu.ecommerce.domain.Product;
import miu.edu.ecommerce.domain.Seller;


import java.util.List;
public interface SellerService {
    List<Seller> getAll();
    Seller getSellerByID(Long id);
    List<Product> getProductsBySellerId(Long id);
}
