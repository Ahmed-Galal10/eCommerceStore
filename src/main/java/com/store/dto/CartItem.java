package com.store.dto;

import com.store.model.entities.CartItemId;
import com.store.model.entities.Product;
import com.store.model.entities.User;

import java.io.Serializable;

public class CartItem implements Serializable {
    private CartItemId id;
    private Product product;
    private User user;
    private int quantity;

    public CartItem(CartItemId id, Product product, User user, int quantity) {
        this.id = id;
        this.product = product;
        this.user = user;
        this.quantity = quantity;
    }

    public CartItem() {
    }

    public CartItemId getId() {
        return id;
    }

    public void setId(CartItemId id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


}
