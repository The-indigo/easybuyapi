package com.example.easybuyapi.Controllers;

import com.example.easybuyapi.models.UserModel;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class RegistrationController {

    @PostMapping("/register")
    public String registerUser(@RequestBody UserModel user) {
        // Your registration logic here
        // You can access user fields using user.getEmail(), user.getPassword(), etc.
        // Return success message or error message accordingly
        return "User registration successful!";
    }
}