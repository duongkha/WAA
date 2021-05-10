package miu.edu.ecommerce.controller;

import miu.edu.ecommerce.domain.Seller;
import miu.edu.ecommerce.service.SellerService;
import miu.edu.ecommerce.service.SellerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/seller")
public class SellerController {
    @Autowired
    SellerService sellerService;

    @GetMapping
    public List<Seller> getAll(){
        return sellerService.getAll();
    }

    @GetMapping("/{id}")
    public Seller getSellerById(@PathVariable("id") Long id){
        return sellerService.getSellerByID(id);
    }
}
