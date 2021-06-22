package com.store.service;

import com.store.dtos.cart.CartDto;
import com.store.dtos.checkout.CheckoutStateDto;

public interface CheckoutService {
    CheckoutStateDto validateCartItemsQuantity(CartDto cartDto);
}
