package com.store.dtos.seller;

import com.store.model.ProdImages;
import com.store.model.Review;
import com.store.model.User;

import java.util.HashSet;
import java.util.Set;

public class ProductDto {
    private Integer id;
    private Integer subcategoryId;
    private User user;
    private String name;
    private String description;
    private int quantity;
    private String img;
    private double price;
    private boolean isOnSale;
    private Set<ProdImages> prodImageses = new HashSet<ProdImages>(0);
    private Set<Review> reviews = new HashSet<Review>(0);


    public ProductDto(Integer id, Integer subcategoryId, User user, String name,
                      String description, int quantity, String img, double price,
                      boolean isOnSale, Set<ProdImages> prodImageses, Set<Review> reviews) {
        this.id = id;
        this.subcategoryId = subcategoryId;
        this.user = user;
        this.name = name;
        this.description = description;
        this.quantity = quantity;
        this.img = img;
        this.price = price;
        this.isOnSale = isOnSale;
        this.prodImageses = prodImageses;
        this.reviews = reviews;
    }

    public ProductDto() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    public Set<ProdImages> getProdImageses() {
        return prodImageses;
    }

    public void setProdImageses(Set<ProdImages> prodImageses) {
        this.prodImageses = prodImageses;
    }

    public Set<Review> getReviews() {
        return reviews;
    }

    public void setReviews(Set<Review> reviews) {
        this.reviews = reviews;
    }

    public Integer getSubcategoryId() {
        return subcategoryId;
    }

    public void setSubcategoryId(Integer subcategoryId) {
        this.subcategoryId = subcategoryId;
    }
}
