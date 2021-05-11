package miu.edu.ecommerce.controller;

import miu.edu.ecommerce.domain.Category;
import miu.edu.ecommerce.domain.Product;
import miu.edu.ecommerce.dto.CategoryDTO;
import miu.edu.ecommerce.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    @Autowired
    CategoryService categoryService;
    @Autowired
    ModelMapper modelMapper;

    @GetMapping
    public List<CategoryDTO> getAll(){
        List<Category> categories = categoryService.getAll();
        return categories.stream().map(c->modelMapper.map(c, CategoryDTO.class)).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public CategoryDTO getCategoryById(@PathVariable("id") Long id){

        Category cat = categoryService.getCategoryById(id);
        return modelMapper.map(cat, CategoryDTO.class);
    }

    @GetMapping("/category")
    public CategoryDTO getCategoryByName(@RequestParam("name") String name){
        Category cat = categoryService.getCategoryByName(name);
        return modelMapper.map(cat, CategoryDTO.class);
    }

    @PostMapping()
    public void createCategory(@RequestBody Category category){
        categoryService.createCategory(category);
    }

}
