package com.example.easybuyapi.repositories;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.easybuyapi.models.User;


public interface UserRepository extends JpaRepository<User,Integer> {
    public User findByEmail(String email);
    
}







