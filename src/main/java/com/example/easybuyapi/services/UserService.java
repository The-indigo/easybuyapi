package com.example.easybuyapi.services;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.easybuyapi.config.JwtService;
import com.example.easybuyapi.models.AuthResponse;
import com.example.easybuyapi.models.Role;
import com.example.easybuyapi.models.User;
import com.example.easybuyapi.repositories.UserRepository;


@Service
public class UserService{
    @Autowired
    UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private AuthenticationManager authenticationManager;

    public User Register(String email,String password,String fullname, String phoneNumber,String address) throws Exception{
        var user=userRepository.findByEmail(email);
        if(user!=null){
            throw new Exception("There is already an account with this user");
        }
        var encrypTedPassword=passwordEncoder.encode(password);
        User userDetails=new User(email,encrypTedPassword,fullname,phoneNumber,address,Role.User);
        userRepository.save(userDetails);
        return userDetails;
    }

    public AuthResponse authenticate(String email,String password){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
        var user=userRepository.findByEmail(email);
        var jwtToken=jwtService.generateToken(user);
        return AuthResponse.builder().id(user.getId()).
        email(user.getEmail()).address(user.getAddress()).fullname(user.getFullname()).
        phoneNumber(user.getPhoneNumber()).role(user.getRole()).
        token(jwtToken).build();
    }
}
