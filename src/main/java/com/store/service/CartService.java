package com.store.service;


import com.store.entities.CartItem;
import com.store.model.cart.CartDto;
import com.store.model.cart.CartItemDto;
import com.store.model.cart.CartItemRequest;

import java.util.List;

public interface CartService {

    CartDto getCartByUserId(Integer userId);

    CartItemDto addCartItem(CartItemRequest cartItemRequest);

    CartItemDto updateCartItem(CartItemRequest cartItemRequest);

    boolean deleteCartItem(CartItemRequest cartItemRequest);

//    CartItemDto addCartItem(int cartId, CartItemRequest cartItemRequest);
//
//    CartItem getByCartItemId(Integer id);




}
