package miu.edu.ecommerce.controller;

import miu.edu.ecommerce.domain.User;
import miu.edu.ecommerce.dto.UserDTO;
import miu.edu.ecommerce.service.UserDetailsImpl;
import miu.edu.ecommerce.service.UserDetailsServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = {"http://localhost:3000"})
public class UserController {

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Autowired
    ModelMapper modelMapper;

    @GetMapping({ "/current" })
    public @ResponseBody UserDTO getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userdetails = (UserDetailsImpl) auth.getPrincipal();
        return modelMapper.map(userdetails.getUser(), UserDTO.class);
    }

}
