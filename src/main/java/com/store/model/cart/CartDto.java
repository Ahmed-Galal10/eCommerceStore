package com.store.model.cart;

import java.util.List;

public class CartDto {


    List<CartItemDto> items;


    public List<CartItemDto> getItems() {
        return items;
    }

    public void setItems(List<CartItemDto> items) {
        this.items = items;
    }
}
