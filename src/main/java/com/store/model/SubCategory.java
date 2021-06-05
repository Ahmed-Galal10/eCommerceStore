package com.store.model;// default package
// Generated Jun 5, 2021, 12:33:40 AM by Hibernate Tools 6.0.0.Alpha2


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * SubCategory generated by hbm2java
 */
@Entity
@Table(name="sub_category"
    ,catalog="ecommerce"
    , uniqueConstraints = @UniqueConstraint(columnNames="name") 
)
public class SubCategory  implements java.io.Serializable {


     private Integer id;
     private Category category;
     private String name;
     private Set<Product> products = new HashSet<Product>(0);

    public SubCategory() {
    }

	
    public SubCategory(Category category, String name) {
        this.category = category;
        this.name = name;
    }
    public SubCategory(Category category, String name, Set<Product> products) {
       this.category = category;
       this.name = name;
       this.products = products;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="id", unique=true, nullable=false)
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="category_id", nullable=false)
    public Category getCategory() {
        return this.category;
    }
    
    public void setCategory(Category category) {
        this.category = category;
    }

    
    @Column(name="name", unique=true, nullable=false, length=50)
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="subCategory")
    public Set<Product> getProducts() {
        return this.products;
    }
    
    public void setProducts(Set<Product> products) {
        this.products = products;
    }




}


