package miu.edu.ecommerce.repository;

import miu.edu.ecommerce.domain.Role;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RoleRepository extends CrudRepository<Role,Long> {
    public List<Role> findAll();
    public Role findRoleById(Long id);
    public Role findRoleByName(String name);
    public List<Role> findRolesByIdIn(List<Long> Ids);
}
