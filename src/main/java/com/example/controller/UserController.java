package com.example.controller;


import com.example.model.User;
import com.example.repository.UserRepository;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;



    @PostMapping("/register")
    public User saveUser(@RequestBody User users){
       return userService.save(users);
    }


    @GetMapping("/user")
    public String home(){
        return "This is the User Page";
    }

    @GetMapping("/admin")
    public String admin() {
        return "This is the Admin Page";
    }
}
