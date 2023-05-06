package com.example.easybuyapi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.easybuyapi.models.Wishlist;
import com.example.easybuyapi.repositories.ProductRepository;
import com.example.easybuyapi.repositories.UserRepository;
import com.example.easybuyapi.repositories.WishlistRepository;

@Service
public class WishlistService{

    @Autowired
    WishlistRepository wishlistRepository;

@Autowired
UserRepository userRepository;

@Autowired
ProductRepository productRepository;

    // public Iterable <Wishlist> getWishlistItemsService(int userId){

    // }

    // public Wishlist addToWishlistService(int userId, int productId){

    // }

    // removeFromWishlistService
    // addToCartFromWishListService
}