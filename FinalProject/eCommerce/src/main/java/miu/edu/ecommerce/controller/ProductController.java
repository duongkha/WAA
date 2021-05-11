package miu.edu.ecommerce.controller;

import miu.edu.ecommerce.domain.Product;
import miu.edu.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("")
    public @ResponseBody
    List<Product> getAllProducts(){
        return productService.getAll();
    }

    @GetMapping("/{productId}")
    public @ResponseBody Optional<Product> getProductById(@PathVariable long productId){
        return productService.getProductById(productId);
    }

    @PostMapping()
    public void createProduct(@RequestBody Product product){
        productService.createProduct(product);
    }

    @DeleteMapping(value = "/{productId}")
    public void deleteProduct(@PathVariable long productId) throws Exception {
        Optional<Product> product =  productService.getProductById(productId);
        try{
            if(product.isPresent()){
                productService.deleteProduct(productId);
            } else{
                throw new EntityNotFoundException("Product does not exist!");
            }
        }catch(Exception e){
            throw new Exception(e.getMessage());
        }
    }
}
