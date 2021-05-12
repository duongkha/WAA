package miu.edu.ecommerce.controller;

import miu.edu.ecommerce.service.AdminService;
import miu.edu.ecommerce.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/admin")
public class AdminController {
    @Autowired
    AdminService adminService;

    @GetMapping("/approve")
    public Boolean approveSeller(@RequestParam("seller") Long id){
        return adminService.approveSeller(id);
    }
}
