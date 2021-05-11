package miu.edu.ecommerce.service;

import miu.edu.ecommerce.domain.Category;
import miu.edu.ecommerce.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CategoryServiceImpl implements CategoryService{

    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category getCategoryById(Long id) {
        return categoryRepository.findCategoryById(id);
    }

    @Override
    public Category getCategoryByName(String name) {
        return categoryRepository.findCategoryByName(name);
    }
}
