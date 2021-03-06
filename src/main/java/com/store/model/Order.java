package com.store.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

/**
 * Order generated by hbm2java
 */
@Entity
@Table(name="order"
    ,catalog="ecommerce"
)
public class Order  implements java.io.Serializable {


     private int id;
     private User user;
     private Date date;
     private Set<Payment> payments = new HashSet<Payment>(0);
     private Set<OrderItem> orderItems = new HashSet<OrderItem>(0);
     private Set<SoldItem> soldItems = new HashSet<SoldItem>(0);

    public Order() {
    }

	
    public Order(int id, User user, Date date) {
        this.id = id;
        this.user = user;
        this.date = date;
    }
    public Order(int id, User user, Date date, Set<Payment> payments, Set<OrderItem> orderItems, Set<SoldItem> soldItems) {
       this.id = id;
       this.user = user;
       this.date = date;
       this.payments = payments;
       this.orderItems = orderItems;
       this.soldItems = soldItems;
    }
   
     @Id 

    //THIS WAS WRONG FROM MAPPER
    @Column(name="id", unique=true, nullable=false)
     @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="date", nullable=false, length=19)
    public Date getDate() {
        return this.date;
    }
    
    public void setDate(Date date) {
        this.date = date;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="order")
    public Set<Payment> getPayments() {
        return this.payments;
    }
    
    public void setPayments(Set<Payment> payments) {
        this.payments = payments;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="order", cascade = CascadeType.ALL)
    public Set<OrderItem> getOrderItems() {
        return this.orderItems;
    }
    
    public void setOrderItems(Set<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="order" , cascade = CascadeType.ALL)
    public Set<SoldItem> getSoldItems() {
        return this.soldItems;
    }
    
    public void setSoldItems(Set<SoldItem> soldItems) {
        this.soldItems = soldItems;
    }




}


