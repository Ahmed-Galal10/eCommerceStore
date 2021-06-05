package com.store.dto;

import java.util.List;

public class ProdDetailDto {

    private Integer id;
    private String prodName;
    private String prodDescription;
    private Double price;
    private Integer quantity;
    private List<String> prodImages;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public String getProdDescription() {
        return prodDescription;
    }

    public void setProdDescription(String prodDescription) {
        this.prodDescription = prodDescription;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public List<String> getProdImages() {
        return prodImages;
    }

    public void setProdImages(List<String> prodImages) {
        this.prodImages = prodImages;
    }

    @Override
    public String toString() {
        return "ProdDetailDto{" +
                "id=" + id +
                ", prodName='" + prodName + '\'' +
                ", prodDescription='" + prodDescription + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", prodImages=" + prodImages +
                '}';
    }
}
