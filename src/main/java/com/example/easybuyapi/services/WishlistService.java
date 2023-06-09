package com.example.easybuyapi.services;

import com.example.easybuyapi.models.Cart;
import com.example.easybuyapi.models.Wishlist;
import com.example.easybuyapi.repositories.CartRepository;
import com.example.easybuyapi.repositories.ProductRepository;
import com.example.easybuyapi.repositories.UserRepository;
import com.example.easybuyapi.repositories.WishlistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WishlistService {

    @Autowired
    private WishlistRepository wishlistRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;

    public boolean addToWishlist(int userId, int productId) {
        // Check if user and product exist
        if (!userRepository.existsById(userId) || !productRepository.existsById(productId)) {
            return false;
        }

        // Check if item already exists in wishlist
        if (wishlistRepository.existsByUserIdAndProductId(userId, productId)) {
            return false;
        }

        // Add item to wishlist
        Wishlist item = new Wishlist();
        item.setUserId(userId);
        item.setProductId(productId);
        wishlistRepository.save(item);

        return true;
    }

    public boolean removeFromWishlist(int userId, int productId) {
        // Check if item exists in wishlist
        Wishlist item = wishlistRepository.findByUserIdAndProductId(userId, productId);
        if (item == null) {
            return false;
            }
            // Remove item from wishlist
    wishlistRepository.delete(item);

    return true;
}
public List<Wishlist> getWishlistByUser(int userId) {
    return wishlistRepository.findByUserId(userId);
}

public List<Wishlist> getWishlistItems(int userId) {
    return wishlistRepository.findByUserId(userId);
}

// public Cart addProductsToCart(int userId) {
//     // Get all items in the wishlist that should be moved to the cart
//     List<Wishlist> wishlistItemsToMove = wishlistRepository.findByUserIdAndMoveToCart(userId, true);

//     // Check if user has an existing cart, if not, create one
//     Cart cart = (Cart) cartRepository.findByuserId(userId);
//     if (cart == null) {
//         cart = new Cart();
//         cart.setUserId(userId);
//         cartRepository.save(cart);
//     }

//     // Add all wishlist items to cart
//     for (Wishlist item : wishlistItemsToMove) {
//         // Check if product exists
//         if (!productRepository.existsById(item.getProductId())) {
//             continue;
//         }

//         // Check if item already exists in cart
//         if (cartRepository.existsByCartIdAndProductId(cart.getId(), item.getProductId())) {
//             continue;
//         }

//         // Add item to cart
//         cartRepository.saveCartItem(cart.getId(), item.getProductId(), cart.getQuantity());
//         wishlistRepository.delete(item);
//     }

//     return cart;
// }



public boolean moveItemToCart(int userId, int productId) {
    // Check if user and product exist
    if (!userRepository.existsById(userId) || !productRepository.existsById(productId)) {
        return false;
    }

    // Check if item exists in wishlist
    Wishlist item = wishlistRepository.findByUserIdAndProductId(userId, productId);
    if (item == null) {
        return false;
    }

    // Remove item from wishlist
    wishlistRepository.delete(item);

    // Add item to cart
    Cart cartItem = new Cart();
    cartItem.setUserId(userId);
    cartItem.setProductId(productId);
    cartRepository.save(cartItem);

    return true;
}
}