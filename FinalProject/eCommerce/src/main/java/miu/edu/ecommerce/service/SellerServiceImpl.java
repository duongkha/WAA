package miu.edu.ecommerce.service;

import miu.edu.ecommerce.domain.Product;
import miu.edu.ecommerce.domain.Seller;
import miu.edu.ecommerce.repository.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SellerServiceImpl implements SellerService{
    @Autowired
    SellerRepository sellerRepository;

    @Override
    public List<Seller> getAll() {
        return sellerRepository.findAll();
    }

    @Override
    public Seller getSellerByID(Long id) {
        return sellerRepository.getSellerById(id);
    }

    @Override
    public List<Product> getProductsBySellerId(Long id) {
        return sellerRepository.getProductsBySellerId(id);
    }


}
