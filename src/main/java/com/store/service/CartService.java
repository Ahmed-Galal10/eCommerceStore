package com.store.service;

import com.store.dto.CartItemDto;
import com.store.dto.CartItemRequest;
import com.store.model.entities.CartItem;
import com.store.model.entities.SubCategory;

public interface CartService {
    CartItemDto addCartItem(int cartId, CartItemRequest cartItemRequest);

    CartItem getByCartItemId(Integer id);
    SubCategory getSub();

}
