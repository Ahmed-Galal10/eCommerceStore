package com.store.controller;


import com.store.model.entities.CartItem;
import com.store.model.entities.CartItemId;
import com.store.model.entities.SubCategory;
import com.store.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class CartItemController {

    private CartService cartService;
    @Autowired
    public void setup(CartService cartService ){
        this.cartService = cartService ;

    }
    @GetMapping("carts/{cartId}/cart-items")
    public CartItem getCartItem(@PathVariable("cartId") int cartId){
        return cartService.getByCartItemId(cartId);

    }
//    @GetMapping("carts/{cartId}/cart-items")
//    public SubCategory getCartItem(@PathVariable("cartId") int cartId){
//        return cartService.getSub();
//
//    }
}
