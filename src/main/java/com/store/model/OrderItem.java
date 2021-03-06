package com.store.model;


import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

/**
 * OrderItem generated by hbm2java
 */
@Entity
@Table(name="order_item"
    ,catalog="ecommerce"
)
public class OrderItem  implements java.io.Serializable {


     private int id;
     @JsonIgnore
     private Order order;
     @JsonIgnore
     private Product product;
     private int quantity;
     private double unitPrice;
     @JsonIgnore
     private Set<SoldItem> soldItems = new HashSet<SoldItem>(0);

    public OrderItem() {
    }

	
    public OrderItem(int id, Order order, Product product,  int quantity, double unitPrice) {
        this.id = id;
        this.order = order;
        this.product = product;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }
    public OrderItem(int id, Order order, Product product, int quantity, double unitPrice, Set<SoldItem> soldItems) {
       this.id = id;
       this.order = order;
       this.product = product;
       this.quantity = quantity;
       this.unitPrice = unitPrice;
       this.soldItems = soldItems;
    }
   
     @Id
    @Column(name="id", unique=true, nullable=false)
     @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="order_id", nullable=false)
    public Order getOrder() {
        return this.order;
    }
    
    public void setOrder(Order order) {
        this.order = order;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="product_id", nullable=false)
    public Product getProduct() {
        return this.product;
    }
    
    public void setProduct(Product product) {
        this.product = product;
    }

    
    @Column(name="quantity", nullable=false)
    public int getQuantity() {
        return this.quantity;
    }
    
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    
    @Column(name="unit_price", nullable=false, precision=22, scale=0)
    public double getUnitPrice() {
        return this.unitPrice;
    }
    
    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="orderItem")
    public Set<SoldItem> getSoldItems() {
        return this.soldItems;
    }
    
    public void setSoldItems(Set<SoldItem> soldItems) {
        this.soldItems = soldItems;
    }




}


