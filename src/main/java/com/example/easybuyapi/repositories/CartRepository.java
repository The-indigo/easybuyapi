package com.example.easybuyapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.easybuyapi.models.CartModel;

public interface CartRepository extends JpaRepository<CartModel, Integer> {
    
}
