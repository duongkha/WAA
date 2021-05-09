package miu.edu.ecommerce.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @RequestMapping({ "/getuserinfo" })
    public String getUserInfo() {

        return "User Info";
    }

}
