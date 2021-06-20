package com.store.dtos.wishlist;

public class WishlistProdRequest {

    private Integer customerId;
    private Integer productId;


    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    @Override
    public String toString() {
        return "WishlistProdRequest{" +
                "customerId=" + customerId +
                ", productId=" + productId +
                '}';
    }
}
