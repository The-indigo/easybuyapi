package com.example.easybuyapi.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.easybuyapi.models.Cart;
import com.example.easybuyapi.models.Product;
import com.example.easybuyapi.repositories.CartRepository;
import com.example.easybuyapi.repositories.ProductRepository;

@Service
public class CartService {
    
    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserService userService;

    public Iterable <Cart> getUserCartService(int id) throws Exception{
        var cartItems=cartRepository.findByuserId(id);
        return cartItems;
    }
    public Cart addToCartService(Integer userId,Integer productId,int quantity) throws Exception{
        var id=userService.getCurrentUserId();
        if(userId==null || productId==null){
        throw new Exception("User or product Field canot be blank..");
        }
        if(userId!=id){
        throw new Exception("You are not currently authenticated..");
        }
        Optional <Product> product=productRepository.findById(productId); 
        if(!product.isPresent()){
            throw new Exception("item not found..");
        }

        Cart cart= cartRepository.findByUserIdAndProductId(userId,productId);
        if(cart!=null){
        throw new Exception("You already have this item in your cart..");
        }

    Cart cartObject=new Cart(userId,productId,quantity);
    cartObject= cartRepository.save(cartObject);
    return cartObject;
    }

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
