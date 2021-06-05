package com.store.controller;



import com.store.model.cart.CartDto;
import com.store.model.cart.CartItemDto;
import com.store.model.cart.CartItemRequest;
import com.store.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping
public class CartItemController {

    private CartService cartService;

    @Autowired
    public void setup(CartService cartService) {
        this.cartService = cartService;

    }

    @GetMapping(path = "customers/{userId}/carts")
    public  ResponseEntity<CartDto> getCart(@PathVariable("userId") Integer userId){

        CartDto cartDto =  cartService.getCartByUserId(userId);
        return ResponseEntity.ok(cartDto);
    }



    @PostMapping(path = "/carts")
    public ResponseEntity<CartItemDto> addCartItem(@RequestBody CartItemRequest cartItemRequest) {

        CartItemDto cartItemDto =  cartService.addCartItem(cartItemRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(cartItemDto) ;
    }


    @PutMapping(path = "/carts")
    public ResponseEntity<CartItemDto> updateCartItem(@RequestBody CartItemRequest cartItemRequest){

        CartItemDto cartItemDto =  cartService.updateCartItem(cartItemRequest);
        return ResponseEntity.ok(cartItemDto);
    }

    @DeleteMapping(path = "/carts")
    public ResponseEntity<Boolean> deleteCartItem(@RequestBody CartItemRequest cartItemRequest){

        boolean isDeleted =  cartService.deleteCartItem(cartItemRequest);
        return ResponseEntity.ok(true);
    }
}
