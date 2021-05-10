package miu.edu.ecommerce.repository;

import miu.edu.ecommerce.domain.Admin;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdminRepository extends CrudRepository<Admin,Long> {
    public List<Admin> findAll();
    public Admin findAdminById(Long id);
}
