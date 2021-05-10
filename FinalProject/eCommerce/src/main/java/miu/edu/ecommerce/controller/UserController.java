package miu.edu.ecommerce.controller;

import miu.edu.ecommerce.domain.User;
import miu.edu.ecommerce.dto.UserDTO;
import miu.edu.ecommerce.service.UserDetailsImpl;
import miu.edu.ecommerce.service.UserDetailsServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Autowired
    ModelMapper modelMapper;

    @GetMapping({ "/current" })
    public @ResponseBody UserDTO getCurrentUser() {
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            UserDetailsImpl userdetails = (UserDetailsImpl) auth.getPrincipal();
            User user = userdetails.getUser();
            return modelMapper.map(user, UserDTO.class);
        }catch (Exception ex){
            throw ex;
        }
    }

}
