package miu.edu.ecommerce.controller;

import lombok.val;
import miu.edu.ecommerce.domain.Seller;
import miu.edu.ecommerce.domain.User;
import miu.edu.ecommerce.dto.SellerDTO;
import miu.edu.ecommerce.dto.UserDTO;
import miu.edu.ecommerce.service.SellerServiceImpl;
import miu.edu.ecommerce.service.UserDetailsImpl;
import miu.edu.ecommerce.service.UserDetailsServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Autowired
    SellerServiceImpl sellerService;

    @Autowired
    ModelMapper modelMapper;

    @GetMapping({ "/current" })
    public @ResponseBody UserDTO getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userdetails = (UserDetailsImpl) auth.getPrincipal();
        return modelMapper.map(userdetails.getUser(), UserDTO.class);
    }
    @GetMapping({ "/mysellerinfo" })
    public @ResponseBody SellerDTO getCurrentSeller() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userdetails = (UserDetailsImpl) auth.getPrincipal();
        List<Seller> sellerList  = sellerService.getAll();
        Optional<Seller> seller = sellerList
                                .stream()
                                .filter(s -> s.getUser().getUsername().compareToIgnoreCase(userdetails.getUsername()) == 0).findFirst();
        if(seller.isPresent())
            return modelMapper.map(seller.get(), SellerDTO.class);
        return null;
    }

}
