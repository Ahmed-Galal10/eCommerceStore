package com.store.dto;

import com.store.model.domain.OrderItem;
import com.store.model.domain.Payment;
import com.store.model.domain.SoldItem;
import com.store.model.domain.User;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class CustomerOrderDto {
    private int id;
    private User user;
    private Date date;
    private Set<Payment> payments = new HashSet<Payment>(0);
    private Set<OrderItem> orderItems = new HashSet<OrderItem>(0);

    public CustomerOrderDto() {
    }

    public CustomerOrderDto(User user, Date date, Set<Payment> payments, Set<OrderItem> orderItems) {
        this.user = user;
        this.date = date;
        this.payments = payments;
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

    public Set<Payment> getPayments() {
        return payments;
    }

    public void setPayments(Set<Payment> payments) {
        this.payments = payments;
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
