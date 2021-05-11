package miu.edu.ecommerce.controller;

import miu.edu.ecommerce.dto.NewUser;
import miu.edu.ecommerce.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/register")
public class RegistrationController {
    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @PostMapping
    public String register(@RequestBody NewUser user){

        return userDetailsService.signUpUser(user);
    }
}
