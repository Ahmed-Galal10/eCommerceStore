package com.store.dtos.customer;

import com.store.model.OrderItem;
import com.store.model.Payment;


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class CustomerOrderDto<User> {
    private int id;
    private User user;
    private Date date;
    private Set<OrderItem> orderItems = new HashSet<>(0);

    public CustomerOrderDto() {

    }

    public CustomerOrderDto(User user, Date date, Set<OrderItem> orderItems) {
        this.user = user;
        this.date = date;
        this.orderItems = orderItems;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }



    public Set<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(Set<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    @Override
    public String toString() {
        return "CustomerOrderDto{" +
                "id=" + id +
                ", user=" + user +
                ", date=" + date +
                '}';
    }
}