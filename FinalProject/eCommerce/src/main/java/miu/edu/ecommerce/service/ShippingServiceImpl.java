package miu.edu.ecommerce.service;

import miu.edu.ecommerce.domain.Shipping;
import miu.edu.ecommerce.repository.ShippingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShippingServiceImpl implements ShippingService{
    @Autowired
    ShippingRepository shippingRepository;

    @Override
    public Shipping createShipping(Shipping shipping) {
        return shippingRepository.save(shipping);
    }
}

