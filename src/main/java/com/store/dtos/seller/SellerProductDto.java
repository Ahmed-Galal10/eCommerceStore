package com.store.dtos.seller;

import com.store.model.User;

public class SellerProductDto {
    private Integer id;
    private Integer subcategoryId;
    private String subcategoryName;
    private String name;
    private String description;
    private int quantity;
    private String img;
    private double price;
    private boolean isOnSale;


    public SellerProductDto(Integer id, Integer subcategoryId, User user, String name,
                            String description, int quantity, String img, double price,
                            boolean isOnSale, String subcategoryName) {
        this.id = id;
        this.subcategoryId = subcategoryId;
        this.subcategoryName = subcategoryName;
        this.name = name;
        this.description = description;
        this.quantity = quantity;
        this.img = img;
        this.price = price;
        this.isOnSale = isOnSale;

    }

    public SellerProductDto() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isOnSale() {
        return isOnSale;
    }

    public void setOnSale(boolean onSale) {
        isOnSale = onSale;
    }

    public Integer getSubcategoryId() {
        return subcategoryId;
    }

    public void setSubcategoryId(Integer subcategoryId) {
        this.subcategoryId = subcategoryId;
    }

    public String getSubcategoryName() {
        return subcategoryName;
    }

    public void setSubcategoryName(String subcategoryName) {
        this.subcategoryName = subcategoryName;
    }

}
