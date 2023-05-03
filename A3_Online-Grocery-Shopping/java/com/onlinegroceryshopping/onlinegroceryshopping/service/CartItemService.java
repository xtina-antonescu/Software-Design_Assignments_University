package com.onlinegroceryshopping.onlinegroceryshopping.service;

import com.onlinegroceryshopping.onlinegroceryshopping.model.CartItem;
import com.onlinegroceryshopping.onlinegroceryshopping.repository.CartItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartItemService {

    @Autowired
    private CartItemRepository cartItemRepository;

    public CartItemService(CartItemRepository cartItemRepository) {
        this.cartItemRepository=cartItemRepository;
    }

    public void save(CartItem cartItem){
        cartItemRepository.save(cartItem);
    }

    public List<CartItem> getAllCartItems(){
        return cartItemRepository.findAll();
    }

    public CartItem getCartItem(Integer id){
        return cartItemRepository.findById(id).get();
    }

    public void deleteCartItem(Integer id){
        cartItemRepository.deleteById(id);
    }

    public List<CartItem> viewCartItems(Integer id){
        List<CartItem> cart = new ArrayList<>();
        for(CartItem item : cartItemRepository.findAll()){
            if(item.getCart().getCartID() == id){
                cart.add(item);
            }
        }
        return cart;
    }
}
