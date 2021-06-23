package com.store.dtos.product;


public class ProductImagesDto {
    private Integer imageId;
    private Integer productId;
    private String imageUrl;

    public Integer getImageId() {
        return imageId;
    }

    public void setImageId(Integer imageId) {
        this.imageId = imageId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString() {
        return "ProductImagesDto{" +
                "imageId=" + imageId +
                ", productId=" + productId +
                ", imageUrl=" + imageUrl +
                '}';
    }
}
