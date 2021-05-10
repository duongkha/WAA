package miu.edu.ecommerce.controller;

import miu.edu.ecommerce.domain.Category;
import miu.edu.ecommerce.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @GetMapping
    public List<Category> getAll(){
        return categoryService.getAll();
    }

    @GetMapping("/{id}")
    public Category getCategoryById(@PathVariable("id") Long id){
        return categoryService.getCategoryById(id);
    }

    @GetMapping("/category")
    public Category getCategoryByName(@RequestParam("name") String name){
        return categoryService.getCategoryByName(name);
    }


}
