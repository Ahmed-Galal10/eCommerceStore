package com.store.dtos.customer;

public class CustomerImageDto {
    private String image;

    public CustomerImageDto(String image) {
        this.image = image;
    }

    public CustomerImageDto() {
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
