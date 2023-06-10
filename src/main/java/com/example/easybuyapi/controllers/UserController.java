package com.example.easybuyapi.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication ;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.easybuyapi.config.JwtService;
import com.example.easybuyapi.models.AuthResponse;
import com.example.easybuyapi.models.User;
import com.example.easybuyapi.services.UserService;

// import org.springframework.web.bind.annotation.CrossOrigin;
// import org.springframework.web.bind.annotation.DeleteMapping;

// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.PutMapping;
// import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/easybuyapi/v1")
public class UserController {
  
    @Autowired 
    private UserService userService;

    @Autowired
    private JwtService jwtService;

    @PostMapping("/register")
    public User addUser(@RequestBody User user) throws Exception{

        return userService.Register(user.getEmail(), user.getPassword(),user.getFullname(), 
        user.getPhoneNumber(), user.getAddress()); 
    }

//     @GetMapping("/authenticated")
//     public String getLoggedin(){
//    Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
//    User userDetails=(User) authentication.getPrincipal();
//    System.out.println(userDetails.getId());

//     }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody Map<String, String>authBody) throws Exception{
        String email=authBody.get("email");
        String password=authBody.get("password");
        return userService.authenticate(email, password);
    }


    
}
