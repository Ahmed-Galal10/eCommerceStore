package com.store.dtos.customer;


public class ProductReviewDto {

    private int id;
    private String name;
    private String description;
    private double price;
    private boolean isOnSale;

    public ProductReviewDto() {
    }

    public ProductReviewDto(String name, String description, double price, boolean isOnSale) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.isOnSale = isOnSale;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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
}
