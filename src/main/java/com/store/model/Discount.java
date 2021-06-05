package com.store.model;// default package
// Generated Jun 5, 2021, 12:33:40 AM by Hibernate Tools 6.0.0.Alpha2


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Discount generated by hbm2java
 */
@Entity
@Table(name="discount"
    ,catalog="ecommerce"
)
public class Discount  implements java.io.Serializable {


     private int id;
     private String code;
     private int percentage;

    public Discount() {
    }

    public Discount(int id, String code, int percentage) {
       this.id = id;
       this.code = code;
       this.percentage = percentage;
    }
   
     @Id 

    
    @Column(name="id", unique=true, nullable=false)
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }

    
    @Column(name="code", nullable=false, length=25)
    public String getCode() {
        return this.code;
    }
    
    public void setCode(String code) {
        this.code = code;
    }

    
    @Column(name="percentage", nullable=false)
    public int getPercentage() {
        return this.percentage;
    }
    
    public void setPercentage(int percentage) {
        this.percentage = percentage;
    }




}


