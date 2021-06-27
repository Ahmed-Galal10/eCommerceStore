package com.store.dtos.checkout;

import com.store.dtos.cart.CartDto;

public class CheckoutStateDto {
    private Boolean state;
    private CartDto cartDto;

    public CheckoutStateDto() {
    }

    public CheckoutStateDto(Boolean state, CartDto cartDto) {
        this.state = state;
        this.cartDto = cartDto;
    }

    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }

    public CartDto getCartDto() {
        return cartDto;
    }

    public void setCartDto(CartDto cartDto) {
        this.cartDto = cartDto;
    }
}
