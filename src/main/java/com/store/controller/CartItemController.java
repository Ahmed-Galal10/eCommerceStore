package com.store.controller;



import com.store.dtos.cart.CartDto;
import com.store.dtos.cart.CartItemDto;
import com.store.dtos.cart.CartItemRequest;
import com.store.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class CartItemController {

    private CartService cartService;

    @Autowired
    public void setup(CartService cartService) {
        this.cartService = cartService;

    }


}
