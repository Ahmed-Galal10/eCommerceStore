package com.store.service;

import com.store.dto.CartItemDto;
import com.store.model.entities.CartItem;
import com.store.model.entities.CartItemId;
import com.store.model.entities.SubCategory;

public interface CartService {
    CartItemDto addCartItem(CartItem cartItem);
    CartItem getByCartItemId(Integer id);
    SubCategory getSub();

}
