package com.store.dtos.product;

import java.util.List;

public class ProdDetailDto {

    private Integer id;
    private String productName;
    private String productDescription;
    private int sellerId;
    private int subcategoryId;
    private Integer productQuantity;
    private String productImg;
    private double productPrice;
    private List<String> prodImages;
    private boolean isOnSale;
    private String sellerName;
    private String subCategoryName;

    public int getSellerId() {
        return sellerId;
    }

    public void setSellerId(int sellerId) {
        this.sellerId = sellerId;
    }

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


    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public String getSubCategoryName() {
        return subCategoryName;
    }

    public void setSubCategoryName(String subCategoryName) {
        this.subCategoryName = subCategoryName;
    }


    @Override
    public String toString() {
        return "ProdDetailDto{" +
                "id=" + id +
                ", productName='" + productName + '\'' +
                ", productDescription='" + productDescription + '\'' +
                ", sellerId=" + sellerId +
                ", subcategoryId=" + subcategoryId +
                ", productQuantity=" + productQuantity +
                ", productImg='" + productImg + '\'' +
                ", productPrice=" + productPrice +
                ", prodImages=" + prodImages +
                ", isOnSale=" + isOnSale +
                '}';
    }



}
