package com.store.dto;

import java.util.List;

public class ProdDetailDto {

    private Integer id;
    private String productName;
    private String productDescription;
    private Double price;
    private Integer productQuantity;
    private String productImg;
    private double productPrice;
    private List<String> prodImages;

    private boolean isOnSale;
    private int subcategoryId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(Integer productQuantity) {
        this.productQuantity = productQuantity;
    }

    public String getProductImg() {
        return productImg;
    }

    public void setProductImg(String productImg) {
        this.productImg = productImg;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public List<String> getProdImages() {
        return prodImages;
    }

    public void setProdImages(List<String> prodImages) {
        this.prodImages = prodImages;
    }

    public boolean isOnSale() {
        return isOnSale;
    }

    public void setOnSale(boolean onSale) {
        isOnSale = onSale;
    }

    public int getSubcategoryId() {
        return subcategoryId;
    }

    public void setSubcategoryId(int subcategoryId) {
        this.subcategoryId = subcategoryId;
    }

    @Override
    public String toString() {
        return "ProdDetailDto{" +
                "id=" + id +
                ", productName='" + productName + '\'' +
                ", productDescription='" + productDescription + '\'' +
                ", price=" + price +
                ", productQuantity=" + productQuantity +
                ", productImg='" + productImg + '\'' +
                ", productPrice=" + productPrice +
                ", prodImages=" + prodImages +
                ", isOnSale=" + isOnSale +
                ", subcategoryId=" + subcategoryId +
                '}';
    }
}
