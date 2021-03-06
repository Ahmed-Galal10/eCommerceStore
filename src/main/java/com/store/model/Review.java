package com.store.model;


import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Review generated by hbm2java
 */
@Entity
@Table(name="review"
    ,catalog="ecommerce"
)
public class Review  implements java.io.Serializable {


     private int id;
     private User user;
     private Product product;
     private Date createdAt;
     private int rating = 0;
     private String reviewText = "";

    public Review() {
    }

	
    public Review(int id, User user, Product product, Date createdAt, int rating) {
        this.id = id;
        this.user = user;
        this.product = product;
        this.createdAt = createdAt;
        this.rating = rating;
    }
    public Review(int id, User user, Product product, Date createdAt, int rating, String reviewText) {
       this.id = id;
       this.user = user;
       this.product = product;
       this.createdAt = createdAt;
       this.rating = rating;
       this.reviewText = reviewText;
    }
   
     @Id 

    
    @Column(name="id", unique=true, nullable=false)
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="customer_id", nullable=false)
    public User getUser() {
        return this.user;
    }
    
    public void setUser(User user) {
        this.user = user;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="product_id", nullable=false)
    public Product getProduct() {
        return this.product;
    }
    
    public void setProduct(Product product) {
        this.product = product;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="created_at", nullable=false, length=19)
    public Date getCreatedAt() {
        return this.createdAt;
    }
    
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    
    @Column(name="rating", nullable=false)
    public int getRating() {
        return this.rating;
    }
    
    public void setRating(int rating) {
        this.rating = rating;
    }

    
    @Column(name="review_text", length=250)
    public String getReviewText() {
        return this.reviewText;
    }
    
    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }




}


