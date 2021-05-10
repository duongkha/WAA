package miu.edu.ecommerce.service;

import miu.edu.ecommerce.domain.Buyer;

import java.util.List;

public interface BuyerService {
    List<Buyer> findAll();
    Buyer findBuyerById(Long id);
}
