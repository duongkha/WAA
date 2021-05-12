package miu.edu.ecommerce.repository;

import miu.edu.ecommerce.domain.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CategoryRepository extends CrudRepository<Category,Long> {
    List<Category> findAll();
    Category findCategoryByName(String name);
    Category findCategoryById(Long id);
    Category save(Category category);
}
