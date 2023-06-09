package com.example.easybuyapi.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.example.easybuyapi.models.AuthResponse;
import com.example.easybuyapi.models.User;
import com.example.easybuyapi.services.UserService;


@RestController
@RequestMapping("/easybuyapi/v1")
public class UserController {
  
    @Autowired 
    private UserService userService;


    @PostMapping("/register")
    public User addUser(@RequestBody User user) throws Exception{

        return userService.Register(user.getEmail(), user.getPassword(),user.getFullname(), 
        user.getPhoneNumber(), user.getAddress()); 
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody Map<String, String>authBody) throws Exception{
        String email=authBody.get("email");
        String password=authBody.get("password");
        return userService.authenticate(email, password);
    }


    
}
