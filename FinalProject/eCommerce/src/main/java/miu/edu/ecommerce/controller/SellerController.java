package miu.edu.ecommerce.controller;

import miu.edu.ecommerce.domain.Product;
import miu.edu.ecommerce.domain.Seller;
import miu.edu.ecommerce.dto.ProductDTO;
import miu.edu.ecommerce.dto.SellerDTO;
import miu.edu.ecommerce.service.SellerService;
import miu.edu.ecommerce.service.SellerServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/sellers")
public class SellerController {
    @Autowired
    SellerService sellerService;
    @Autowired
    ModelMapper modelMapper;

    @GetMapping
    public List<SellerDTO> getAll(){

        List<Seller> sellers = sellerService.getAll();
        return sellers.stream().map(s->modelMapper.map(s, SellerDTO.class)).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public SellerDTO getSellerById(@PathVariable("id") Long id){
        Seller seller = sellerService.getSellerByID(id);
        return modelMapper.map(seller, SellerDTO.class);
    }
    //Get products by seller
    @GetMapping("/{id}/products")
    public List<ProductDTO> getProductsBySellerId(@PathVariable("id") Long id){
        List<Product> products = sellerService.getProductsBySellerId(id);
        return products.stream()
                .map(p -> modelMapper.map(p, ProductDTO.class))
                .collect(Collectors.toList());
    }


}
