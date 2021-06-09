package com.store.dtos.customer;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;


public class CustomerReviewDto {

    private int id;

    private ProductReviewDto product;
    private Date createdAt;
    private int rating;
    private String reviewText;

    public CustomerReviewDto() {
    }

    public CustomerReviewDto(ProductReviewDto product, Date createdAt, int rating, String reviewText) {
        this.product = product;
        this.createdAt = createdAt;
        this.rating = rating;
        this.reviewText = reviewText;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ProductReviewDto getProduct() {
        return product;
    }

    public void setProduct(ProductReviewDto product) {
        this.product = product;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getReviewText() {
        return reviewText;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }
}
