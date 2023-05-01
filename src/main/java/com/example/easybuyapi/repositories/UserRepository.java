package com.example.easybuyapi.repositories;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.easybuyapi.models.UserModel;


public interface UserRepository extends JpaRepository<UserModel,Integer> {
    
}







