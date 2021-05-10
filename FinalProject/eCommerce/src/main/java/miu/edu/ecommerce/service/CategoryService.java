package miu.edu.ecommerce.service;

import miu.edu.ecommerce.domain.Category;

import java.util.List;

public interface CategoryService {
    List<Category> getAll();
    Category getCategoryById(Long id);
    Category getCategoryByName(String name);
}
