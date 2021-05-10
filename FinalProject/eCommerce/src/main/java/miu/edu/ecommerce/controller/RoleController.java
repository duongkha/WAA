package miu.edu.ecommerce.controller;

import miu.edu.ecommerce.domain.Role;
import miu.edu.ecommerce.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roles")
public class RoleController {
    @Autowired
    RoleService roleService;

    @GetMapping
    public List<Role> getAll(){
        return roleService.findAll();
    }

    @GetMapping("/{id}")
    public Role getRoleById(@PathVariable("id") Long id){
        return roleService.findRoleById(id);
    }

    @GetMapping("/role")
    public Role getRoleByName(@RequestParam("name") String name){
        return roleService.findRoleByName(name);
    }

}
