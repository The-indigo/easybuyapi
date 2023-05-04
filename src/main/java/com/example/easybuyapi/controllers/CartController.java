package com.example.easybuyapi.controllers;

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

import com.example.easybuyapi.models.Cart;
import com.example.easybuyapi.models.Product;
import com.example.easybuyapi.models.Wishlist;
import com.example.easybuyapi.repositories.CartRepository;
import com.example.easybuyapi.repositories.ProductRepository;
import com.example.easybuyapi.repositories.UserRepository;
import com.example.easybuyapi.repositories.WishlistRepository;

@RestController
@RequestMapping("/cart")
public class CartController {
    
    @Autowired
    private WishlistRepository wishlistRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private CartRepository cartRepository;
    
    @Autowired
    private ProductRepository productRepository;
    
    @DeleteMapping("/easybuyapi/v1/{cartId}")
    public ResponseEntity<?> decreaseItemFromCart(@PathVariable int cartId) {
        // Check if item exists in cart
        Cart item = cartRepository.findById(cartId);
        if (item == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Item not found in wishlist.");
        }
        if (item.getQuantity() == 1) {
        	cartRepository.delete(item);
        }
        else {
        	int initialQuantity = item.getQuantity();
        	item.setQuantity(initialQuantity-1);
        	cartRepository.save(item);
        }
        
        
        return ResponseEntity.status(HttpStatus.OK).body("Item removed from wishlist.");
    }

    
}