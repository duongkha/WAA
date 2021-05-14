package miu.edu.ecommerce.controller;

import miu.edu.ecommerce.domain.Product;
import miu.edu.ecommerce.domain.Review;
import miu.edu.ecommerce.domain.Seller;
import miu.edu.ecommerce.dto.ProductDTO;
import miu.edu.ecommerce.dto.ReviewDTO;
import miu.edu.ecommerce.dto.SellerDTO;
import miu.edu.ecommerce.dto.UserDTO;
import miu.edu.ecommerce.service.ProductService;
import miu.edu.ecommerce.service.SellerService;
import miu.edu.ecommerce.service.UserDetailsImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping("api/products")
public class ProductController {
    @Autowired
    private ProductService productService;



    @Autowired
    ModelMapper modelMapper;

    @GetMapping("")
    public @ResponseBody
    List<ProductDTO> getAllProducts(){
        List<Product> products = productService.getAll();
        return products.stream()
                .map(p -> modelMapper.map(p,ProductDTO.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/{productId}")
    public @ResponseBody ProductDTO getProductById(@PathVariable Long productId){
        Optional<Product> productOptional = productService.getProductById(productId);
        if(productOptional.isPresent()){
            return modelMapper.map(productOptional.get(), ProductDTO.class);
        }
        return null;
    }

    @PostMapping("/new")
    @PreAuthorize("hasAutority('SELLER')")
    public Boolean createProduct(@RequestBody ProductDTO productDTO){
        Product product = modelMapper.map(productDTO, Product.class);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userdetails = (UserDetailsImpl) auth.getPrincipal();
        return productService.createProduct(product, userdetails.getUser().getId());
    }

    @PutMapping("")
    public void updateProduct(@RequestBody Product product){
        productService.updateProduct(product);
    }


    @DeleteMapping(value = "/{productId}")
    public Boolean deleteProduct(@PathVariable Long productId) throws Exception {
        Optional<Product> product =  productService.getProductById(productId);
        try{
            if(product.isPresent()){
                productService.deleteProduct(productId);
                return true;
            } else{
                throw new EntityNotFoundException("Product does not exist!");
            }
        }catch(Exception e){
            throw new Exception(e.getMessage());
        }
    }
    //Get approved reviews by product id
    @GetMapping("{productId}/reviews")
    public @ResponseBody
    List<ReviewDTO> getApprovedReviewsByProductId(@PathVariable Long productId){
        List<Review> reviews = productService.getApprovedReviewsByProductId(productId);
        return reviews.stream()
                .map(r->modelMapper.map(r,ReviewDTO.class))
                .collect(Collectors.toList());
    }

}
