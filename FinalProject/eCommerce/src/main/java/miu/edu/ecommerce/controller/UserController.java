package miu.edu.ecommerce.controller;

import jdk.nashorn.internal.runtime.options.Option;
import lombok.val;
import miu.edu.ecommerce.domain.Buyer;
import miu.edu.ecommerce.domain.Seller;
import miu.edu.ecommerce.domain.User;
import miu.edu.ecommerce.dto.BuyerDTO;
import miu.edu.ecommerce.dto.SellerDTO;
import miu.edu.ecommerce.dto.UserDTO;
import miu.edu.ecommerce.service.BuyerService;
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
    BuyerService buyerService;

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

    @GetMapping({ "/mybuyerinfo" })
    public @ResponseBody
    BuyerDTO getCurrentBuyer() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userdetails = (UserDetailsImpl) auth.getPrincipal();
        Optional<Buyer> buyer =  buyerService.findAll().stream().filter(x->x.getUser().getUsername().equalsIgnoreCase(userdetails.getUsername())).findFirst();
        if(buyer.isPresent())
            return modelMapper.map(buyer.get(), BuyerDTO.class);
        return null;
    }

}
