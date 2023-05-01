package com.example.easybuyapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.easybuyapi.models.WishlistModel;

public interface WishlistRepository extends JpaRepository<WishlistModel, Integer> {
    
}
