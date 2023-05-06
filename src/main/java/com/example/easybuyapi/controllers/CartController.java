package com.example.easybuyapi.controllers;


import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.easybuyapi.models.Cart;

// import com.example.easybuyapi.repositories.CartRepository;
import com.example.easybuyapi.services.CartService;


@RestController
@RequestMapping("/easybuyapi/v1")
public class CartController {
    
@Autowired 
CartService cartService;

@GetMapping("/cart")
public Iterable<Cart> getCartItems(@RequestBody Map<String, Integer> user ) throws Exception{
    int userId=user.get("user");
    return cartService.getUserCartService(userId);
}

@PostMapping("/cart/quantity/{id}")
public void updateQuantity(@PathVariable("id") int id,@RequestBody Map<String, Integer> body ) throws Exception{
    int quantity=body.get("quantity");
    cartService.updateCartQuantityService(id, quantity);
}

@DeleteMapping("/cart/delete/{id}")
public String deleteCartItem(@PathVariable("id") int id) throws Exception{
    cartService.deleteCartItemService(id);
    return ("Item successfully removed");
}
    
}