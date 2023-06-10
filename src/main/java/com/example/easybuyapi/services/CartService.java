package com.example.easybuyapi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.easybuyapi.models.Cart;
import com.example.easybuyapi.repositories.CartRepository;

@Service
public class CartService {
    
    @Autowired
    private CartRepository cartRepository;

    public Iterable <Cart> getUserCartService(int id) throws Exception{
        var cartItems=cartRepository.findByuserId(id);
        return cartItems;
    }
    // public Cart addToCartService(int userId,int productId,int quantity) throws Exception{
        
    // }

    public void updateCartQuantityService(int id,int quantity) throws Exception{
        var item=cartRepository.findById(id).get();
        if(item==null){
            throw new Exception("Item not in cart");
        }
        item.setQuantity(quantity);
        cartRepository.save(item);
    }

    public void deleteCartItemService(int id) throws Exception{
        var item=cartRepository.findById(id);
        if(item.isEmpty()){
            throw new Exception("Item not in cart");
        }
        cartRepository.deleteById(id);

    }
}
