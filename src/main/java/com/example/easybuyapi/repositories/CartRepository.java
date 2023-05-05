package com.example.easybuyapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.easybuyapi.models.Cart;

public interface CartRepository extends JpaRepository<Cart, Integer> {
    Iterable <Cart> findByuserid(int userId);
	// Cart findByUserIdAndProductId(int userId, int productId); //finds an item in the cart
}
