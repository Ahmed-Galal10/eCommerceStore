package com.store.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@DiscriminatorValue("ROLE_CUSTOMER")
public class Customer extends  User {

    private double balance;

    private Set<Review> reviews = new HashSet<Review>(0);
    private Set<CartItem> cartItems = new HashSet<CartItem>(0);
    private Set<Order> orders = new HashSet<Order>(0);


    @OneToMany(fetch= FetchType.LAZY, mappedBy="user")
    public Set<Review> getReviews() {
        return this.reviews;
    }

    public void setReviews(Set<Review> reviews) {
        this.reviews = reviews;
    }

    @OneToMany(fetch=FetchType.LAZY, mappedBy="user")
    public Set<CartItem> getCartItems() {
        return this.cartItems;
    }

    public void setCartItems(Set<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    @OneToMany(fetch=FetchType.LAZY, mappedBy="user")
    public Set<Order> getOrders() {
        return this.orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }


    @Column(name="balance", nullable=false, precision=22, scale=0)
    public double getBalance() {
        return this.balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }



}
