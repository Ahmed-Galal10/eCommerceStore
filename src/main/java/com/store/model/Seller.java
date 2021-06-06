package com.store.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@DiscriminatorValue("ROLE_SELLER")
public class Seller extends  User {

    private double balance;

    private Set<Product> products = new HashSet<Product>(0);
    private Set<SoldItem> soldItems = new HashSet<SoldItem>(0);



    @OneToMany(fetch= FetchType.LAZY, mappedBy="user")
    public Set<Product> getProducts() {
        return this.products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    @OneToMany(fetch=FetchType.LAZY, mappedBy="user")
    public Set<SoldItem> getSoldItems() {
        return this.soldItems;
    }

    public void setSoldItems(Set<SoldItem> soldItems) {
        this.soldItems = soldItems;
    }

    @Column(name="balance", nullable=false, precision=22, scale=0)
    public double getBalance() {
        return this.balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }





}
