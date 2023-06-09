package com.example.easybuyapi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.easybuyapi.models.Wishlist;

public interface WishlistRepository extends JpaRepository<Wishlist, Integer> {
    /*checks if the item exists in the wishlist*/
    boolean existsByUserIdAndProductId(int userId, int productId); 

    /*finds an item in the wishlist*/
    Wishlist findByUserIdAndProductId(int userId, int productId); 
    
    /*all items in the wishlist*/
    List<Wishlist> findByUserId(int userId); 
}
