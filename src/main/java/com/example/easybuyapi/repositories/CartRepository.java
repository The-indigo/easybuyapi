package com.example.easybuyapi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.example.easybuyapi.models.Cart;

import jakarta.transaction.Transactional;

public interface CartRepository extends JpaRepository<Cart, Integer> {
    Iterable <Cart> findByuserId(int userId);
	Cart findByUserIdAndProductId(int userId, int productId); //finds an item in the cart
    boolean existsByCartIdAndProductId(int cartId, int productId); //checks if the item already exists in cart
    //List<Cart> findByUserId(int userId);
    
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO cart_items (cart_id, product_id, quantity) VALUES (?1, ?2, ?3)", nativeQuery = true)
    void saveCartItem(int cartId, int productId, int quantity);
    
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM cart_items WHERE cart_id = ?1 AND product_id = ?2", nativeQuery = true)
    void deleteCartItem(int cartId, int productId);
}
