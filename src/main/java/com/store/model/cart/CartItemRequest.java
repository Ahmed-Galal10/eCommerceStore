package com.store.model.cart;

public class CartItemRequest {

    int customerId;
    int productId;
    int quantity;


    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


    @Override
    public String toString() {
        return "CartItemRequest{" +
                "customerId=" + customerId +
                ", productId=" + productId +
                ", quantity=" + quantity +
                '}';
    }
}
