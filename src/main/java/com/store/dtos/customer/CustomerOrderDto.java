package com.store.dtos.customer;

import com.store.model.OrderItem;


import java.util.*;

public class CustomerOrderDto {
    private int id;
    private Date date;
    private List<OrderItem> orderItems = new ArrayList<>(0);

    public CustomerOrderDto() {

    }

    public CustomerOrderDto(Date date, List<OrderItem> orderItems) {
        this.date = date;
        this.orderItems = orderItems;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    @Override
    public String toString() {
        return "CustomerOrderDto{" +
                "id=" + id +
                ", date=" + date +
                '}';
    }
}