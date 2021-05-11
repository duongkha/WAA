package miu.edu.ecommerce.service;

import miu.edu.ecommerce.domain.Buyer;
import miu.edu.ecommerce.repository.BuyerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BuyerServiceImpl implements BuyerService{
    @Autowired
    BuyerRepository buyerRepository;
    @Override
    public List<Buyer> findAll() {
        return buyerRepository.findAll();
    }

    @Override
    public Buyer findBuyerById(Long id) {
        return buyerRepository.findBuyerById(id);
    }
}
