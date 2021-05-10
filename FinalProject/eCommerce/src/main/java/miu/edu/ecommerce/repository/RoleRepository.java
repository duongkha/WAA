package miu.edu.ecommerce.repository;

import miu.edu.ecommerce.domain.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role,Long> {
    public Role getRoleById(Long id);
}
