package com.example.easybuyapi.controllers;

import com.example.easybuyapi.models.Cart;
import com.example.easybuyapi.models.Wishlist;
import com.example.easybuyapi.repositories.CartRepository;
import com.example.easybuyapi.repositories.ProductRepository;
import com.example.easybuyapi.repositories.UserRepository;
import com.example.easybuyapi.services.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/wishlist")
public class WishlistController {

    @Autowired
    private WishlistService wishlistService;

    @PostMapping("/easybuyapi/v1/{userId}/{productId}")
    public ResponseEntity<?> addToWishlist(@PathVariable int userId, @PathVariable int productId) {
        boolean isAdded = wishlistService.addToWishlist(userId, productId);
        if (!isAdded) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Item already exists in wishlist.");
        }
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/easybuyapi/v1/{userId}/{productId}")
    public ResponseEntity<?> removeFromWishlist(@PathVariable int userId, @PathVariable int productId) {
        boolean isRemoved = wishlistService.removeFromWishlist(userId, productId);
        if (!isRemoved) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Item not found in wishlist.");
        }
        return ResponseEntity.status(HttpStatus.OK).body("Item removed from wishlist.");
    }

    @GetMapping("/easybuyapi/v1/{userId}")
    public ResponseEntity<?> getWishlistItems(@PathVariable int userId) {
        List<Wishlist> items = wishlistService.getWishlistItems(userId);
        if (items == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(items);
    }

    @PostMapping("/easybuyapi/v1/cart/{wishlistId}")
    public ResponseEntity<?> addProductsFromWishList(@PathVariable int wishlistId) {
        Cart cartItem = wishlistService.addProductsToCart(wishlistId);
        if (cartItem == null) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(cartItem);
    }

}
