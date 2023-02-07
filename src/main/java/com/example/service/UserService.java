package com.example.service;

import com.example.model.User;
import com.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public User save(@RequestBody User users) {
        User user= new User();
        user.setUsername(users.getUsername());
        user.setPassword(passwordEncoder.encode(users.getPassword()));
        user.setRole(users.getRole());
        userRepository.save(user);
        return user;
    }


}
