package com.store.dtos.cart;

import java.util.List;

public class CartDto {

    private Integer customerId;
    private List<CartItemDto> items;


    public List<CartItemDto> getItems() {
        return items;
    }

    public void setItems(List<CartItemDto> items) {
        this.items = items;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }
}
