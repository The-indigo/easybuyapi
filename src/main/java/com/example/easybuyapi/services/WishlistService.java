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

    @Autowired
    private UserService userService;

    public final Wishlist addToWishlist(final int userId, 
    final int productId) throws Exception {
        var id=userService.getCurrentUserId();
        if(userId!=id){
        throw new Exception("You are not currently authenticated");
        }else{
        // Check if user and product exist
     if (!userRepository.existsById(userId) || !productRepository.existsById(productId)) {
        throw new Exception("Cannot find user or product...");
    }

    // Check if item already exists in wishlist
    if (wishlistRepository.existsByUserIdAndProductId(userId, productId)) {
        throw new Exception("This item is already in your wishlist...");
    }

    // Add item to wishlist
    Wishlist item = new Wishlist(userId, productId);
    item = wishlistRepository.save(item);
    return item;
        }
   
    }

    public final boolean removeFromWishlist(final int userId, final int productId) {
        // Check if item exists in wishlist
        Wishlist item = wishlistRepository.findByUserIdAndProductId(userId, productId);
        if (item == null) {
            return false;
        }
        // Remove item from wishlist
        wishlistRepository.delete(item);

        return true;
    }

    public final List<Wishlist> getWishlistByUser(final int userId) {
        return wishlistRepository.findByUserId(userId);
    }

    public final List<Wishlist> getWishlistItems(final int userId) {
        return wishlistRepository.findByUserId(userId);
    }

    // public Cart addProductsToCart(int userId) {
    // // Get all items in the wishlist that should be moved to the cart
    // List<Wishlist> wishlistItemsToMove =
    // wishlistRepository.findByUserIdAndMoveToCart(userId, true);

    // // Check if user has an existing cart, if not, create one
    // Cart cart = (Cart) cartRepository.findByuserId(userId);
    // if (cart == null) {
    // cart = new Cart();
    // cart.setUserId(userId);
    // cartRepository.save(cart);
    // }

    // // Add all wishlist items to cart
    // for (Wishlist item : wishlistItemsToMove) {
    // // Check if product exists
    // if (!productRepository.existsById(item.getProductId())) {
    // continue;
    // }

    // // Check if item already exists in cart
    // if (cartRepository.existsByCartIdAndProductId(cart.getId(),
    // item.getProductId())) {
    // continue;
    // }

    // // Add item to cart
    // cartRepository.saveCartItem(cart.getId(), item.getProductId(),
    // cart.getQuantity());
    // wishlistRepository.delete(item);
    // }

    // return cart;
    // }

    public final boolean moveItemToCart(final int userId, final int productId) {
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