package com.store.dto;

public class CartItemRequest {
    private Integer productId;
    private Integer userId;
    private Integer quantity;

    public CartItemRequest() {

    }

    public CartItemRequest(Integer productId, Integer userId, Integer quantity) {
        this.quantity = quantity;
        this.userId = userId;
        this.productId = productId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }


}
