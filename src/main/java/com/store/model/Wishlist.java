package com.store.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="wishlist" ,catalog="ecommerce")
public class Wishlist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @OneToOne
    @JoinColumn(name = "customer_id")
    private  Customer customer;

    @ManyToMany
    @JoinTable(name="product_wishlist",
            joinColumns =  @JoinColumn(name = "wishlist_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")  )
    private Set<Product> products = new HashSet<>(0);


}
