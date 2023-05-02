package com.example.easybuyapi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.easybuyapi.models.ProductModel;
import com.example.easybuyapi.models.WishlistModel;

public interface WishlistRepository extends JpaRepository<WishlistModel, Integer> {
    boolean existsByUserIdAndProductId(int userId, int productId); //checks if the item exists in the wishlist
    WishlistModel findByUserIdAndProductId(int userId, int productId); //finds an item in the wishlist
    List<WishlistModel> findByUserId(int userId); //all items in the wishlist
    WishlistModel findById(int id);

}
