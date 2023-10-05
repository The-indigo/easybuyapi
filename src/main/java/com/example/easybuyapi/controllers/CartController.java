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
@RequestMapping("/easybuyapi/v1/cart")
public class CartController {

    /**
     * Indicates that the CartService should be autowired
     * by spring's dependency injection mechanism.
     **/
@Autowired
private CartService cartService;


  /**
     * This method calls the getUserCartService method in
     *  the services module. Return a list of the user's
     * cart items.
     * @return Iterable<Cart>
     * @param user
     **/
@GetMapping("/")
public Iterable<Cart> getCartItems(
    @RequestBody Map<String, Integer> user ) throws Exception{
    int userId = user.get("user");
    return cartService.getUserCartService(userId);
}

 /**
     * This method calls the addToCartService method in
     *  the cart services module to add a cart item value .
     * @param newCartItem
     **/
@PostMapping("/")
public Cart addToCart(@RequestBody Cart newCartItem) throws Exception{
    return cartService.addToCartService(newCartItem.getUserId(),
    newCartItem.getProductId(), newCartItem.getQuantity());
}
  /**
     * This method calls the updateCartQuantityService method in
     *  the cart services module to update a cart item value .
     * @param id
     * @param body
     **/
@PostMapping("/quantity/{id}")
public void updateQuantity(@PathVariable("id") int id,
@RequestBody Map<String, Integer> body ) throws Exception{
    int quantity = body.get("quantity");
    cartService.updateCartQuantityService(id, quantity);
}


  /**
     * This method calls the deleteCartItemService method in
     *  the  cart services module.
     * @return string.
     * @param id
     **/
@DeleteMapping("/delete/{id}")
public String deleteCartItem(@PathVariable("id") int id) throws Exception{
    cartService.deleteCartItemService(id);
    return ("Item successfully removed");
}
}