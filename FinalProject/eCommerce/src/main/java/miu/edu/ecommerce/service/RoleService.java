package miu.edu.ecommerce.service;

import miu.edu.ecommerce.domain.Role;

import java.util.List;

public interface RoleService {
    List<Role> findAll();
    Role findRoleById(Long id);
    Role findRoleByName(String name);
}
