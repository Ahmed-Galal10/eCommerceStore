package com.store.dtos.customer;


public class ProductWishListDto {

    private int id;
    private String name;
    private double price;
    private boolean isOnSale;
    private String img;
    private double rating;

    public ProductWishListDto() {
    }

    public ProductWishListDto(int id, String name, double price, boolean isOnSale, String img, double rating) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.isOnSale = isOnSale;
        this.img = img;
        this.rating = rating;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
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

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
