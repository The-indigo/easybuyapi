package com.example.easybuyapi.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.easybuyapi.models.CartModel;
import com.example.easybuyapi.models.WishlistModel;
import com.example.easybuyapi.repositories.CartRepository;
import com.example.easybuyapi.repositories.ProductRepository;
import com.example.easybuyapi.repositories.UserRepository;
import com.example.easybuyapi.repositories.WishlistRepository;

@RestController
@RequestMapping("/wishlist")
public class WishlistController {
    
    @Autowired
    private WishlistRepository wishlistRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private ProductRepository productRepository;
    
    @PostMapping("/easybuyapi/v1/{userId}/{productId}")
    public ResponseEntity<?> addToWishlist(@PathVariable int userId, @PathVariable int productId) {
        // Check if user and product exist
        if (!userRepository.existsById(userId) || !productRepository.existsById(productId)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User or product not found.");
        }
        
        // Check if item already exists in wishlist
        if (wishlistRepository.existsByUserIdAndProductId(userId, productId)) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Item already exists in wishlist.");
        }
        
        // Add item to wishlist
        WishlistModel item = new WishlistModel();
        item.setUserId(userId);
        item.setProductId(productId);
        wishlistRepository.save(item);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(item);
    }
    
    @DeleteMapping("/easybuyapi/v1/{userId}/{productId}")
    public ResponseEntity<?> removeFromWishlist(@PathVariable int userId, @PathVariable int productId) {
        // Check if item exists in wishlist
        WishlistModel item = wishlistRepository.findByUserIdAndProductId(userId, productId);
        if (item == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Item not found in wishlist.");
        }
        
        wishlistRepository.delete(item);
        
        return ResponseEntity.status(HttpStatus.OK).body("Item removed from wishlist.");
    }
    
    @GetMapping("/easybuyapi/v1/{userId}")
    public ResponseEntity<?> getWishlistItems(@PathVariable int userId) {
        // Check if user exists
        if (!userRepository.existsById(userId)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
        }
        
        List<WishlistModel> items = wishlistRepository.findByUserId(userId);
        
        return ResponseEntity.status(HttpStatus.OK).body(items);
    }
    
}
