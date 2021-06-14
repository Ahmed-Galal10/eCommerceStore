package com.store.dtos.customer;

import com.store.model.Product;

import java.util.Set;

public class CustomerWishListDto {

    private int id;
    private Set<ProductWishListDto> products;

    public CustomerWishListDto() {

    }

    public CustomerWishListDto(int id, Set<ProductWishListDto> products) {
        this.id = id;
        this.products = products;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Set<ProductWishListDto> getProducts() {
        return products;
    }

    public void setProducts(Set<ProductWishListDto> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "CustomerWishListDto{" +
                "id=" + id +
                ", products=" + products +
                '}';
    }
}