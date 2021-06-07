package com.store.service;


import com.store.dtos.cart.CartDto;
import com.store.dtos.cart.CartItemDto;
import com.store.dtos.cart.CartItemRequest;

public interface CartService {

    CartDto getCartByUserId(Integer userId);

    CartItemDto addCartItem(CartItemRequest cartItemRequest);

    CartItemDto updateCartItem(CartItemRequest cartItemRequest);

    boolean deleteCartItem(CartItemRequest cartItemRequest);

}
