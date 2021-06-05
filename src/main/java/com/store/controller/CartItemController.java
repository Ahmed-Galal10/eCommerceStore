package com.store.controller;


import com.store.dto.CartItemDto;
import com.store.dto.CartItemRequest;
import com.store.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class CartItemController {

    private CartService cartService;

    @Autowired
    public void setup(CartService cartService) {
        this.cartService = cartService;

    }

    @PostMapping("carts/{cartId}/cart-items")
    public CartItemDto addCartItem(@PathVariable("cartId") int cartId, @RequestBody CartItemRequest cartItemRequest) {
        return cartService.addCartItem(cartId, cartItemRequest);

    }

//    @GetMapping("carts/{cartId}/cart-items")
//    public SubCategory getCartItem(@PathVariable("cartId") int cartId){
//        return cartService.getSub();
//
//    }
}
