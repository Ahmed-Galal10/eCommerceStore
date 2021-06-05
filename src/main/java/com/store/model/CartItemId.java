package com.store.model;// default package
// Generated Jun 5, 2021, 12:33:40 AM by Hibernate Tools 6.0.0.Alpha2


import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * CartItemId generated by hbm2java
 */
@Embeddable
public class CartItemId  implements java.io.Serializable {


     private int customerId;
     private int prodId;

    public CartItemId() {
    }

    public CartItemId(int customerId, int prodId) {
       this.customerId = customerId;
       this.prodId = prodId;
    }
   


    @Column(name="customer_id", nullable=false)
    public int getCustomerId() {
        return this.customerId;
    }
    
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }


    @Column(name="prod_id", nullable=false)
    public int getProdId() {
        return this.prodId;
    }
    
    public void setProdId(int prodId) {
        this.prodId = prodId;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof CartItemId) ) return false;
		 CartItemId castOther = ( CartItemId ) other; 
         
		 return (this.getCustomerId()==castOther.getCustomerId())
 && (this.getProdId()==castOther.getProdId());
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + this.getCustomerId();
         result = 37 * result + this.getProdId();
         return result;
   }   


}


