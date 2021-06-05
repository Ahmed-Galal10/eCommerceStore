package com.store.db.domain;// default package
// Generated Jun 5, 2021, 12:33:40 AM by Hibernate Tools 6.0.0.Alpha2


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * ProdImages generated by hbm2java
 */
@Entity
@Table(name="prod_images"
    ,catalog="ecommerce"
)
public class ProdImages  implements java.io.Serializable {


     private Integer imageId;
     private Product product;
     private String imageUrl;

    public ProdImages() {
    }

    public ProdImages(Product product, String imageUrl) {
       this.product = product;
       this.imageUrl = imageUrl;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="image_id", unique=true, nullable=false)
    public Integer getImageId() {
        return this.imageId;
    }
    
    public void setImageId(Integer imageId) {
        this.imageId = imageId;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="prod_id", nullable=false)
    public Product getProduct() {
        return this.product;
    }
    
    public void setProduct(Product product) {
        this.product = product;
    }

    
    @Column(name="image_url", nullable=false, length=200)
    public String getImageUrl() {
        return this.imageUrl;
    }
    
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }




}


