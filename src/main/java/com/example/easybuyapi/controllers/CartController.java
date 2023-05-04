package com.example.easybuyapi.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.easybuyapi.models.Cart;

import com.example.easybuyapi.repositories.CartRepository;


@RestController
@RequestMapping("/cart")
public class CartController {
    

    
    @Autowired
    private CartRepository cartRepository;
  
    
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