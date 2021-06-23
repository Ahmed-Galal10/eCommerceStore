package com.store.dtos.review;

import java.util.Date;

public class ReviewDto {
    private int id;
    private Integer userId;
    private String userName;
    private Integer productId;
    private Date createdDate;
    private int rating;
    private String reviewText;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdAt) {
        this.createdDate = createdAt;
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

    @Override
    public String toString() {
        return "ReviewDto{" +
                "id=" + id +
                ", userId=" + userId +
                ", productId=" + productId +
                ", createdAt=" + createdDate +
                ", rating=" + rating +
                ", reviewText='" + reviewText + '\'' +
                '}';
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
