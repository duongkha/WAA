package miu.edu.ecommerce.service;


import miu.edu.ecommerce.domain.Seller;
import miu.edu.ecommerce.repository.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
public interface SellerService {
    List<Seller> getAll();
    Seller getSellerByID(Long id);

}
