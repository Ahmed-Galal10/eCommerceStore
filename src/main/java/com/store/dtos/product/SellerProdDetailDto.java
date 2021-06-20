package com.store.dtos.product;


public class SellerProdDetailDto {

    ProdDetailDto sellerProduct = new ProdDetailDto();
    Integer soldCounter;
    Integer wishListCounter;
    Double averageRating;
    Integer categoryId;

    public SellerProdDetailDto() {
    }

    public ProdDetailDto getSellerProduct() {
        return sellerProduct;
    }

    public void setSellerProduct(ProdDetailDto sellerProduct) {
        this.sellerProduct = sellerProduct;
    }

    public Integer getSoldCounter() {
        return soldCounter;
    }

    public void setSoldCounter(Integer soldCounter) {
        this.soldCounter = soldCounter;
    }

    public Integer getWishListCounter() {
        return wishListCounter;
    }

    public void setWishListCounter(Integer wishListCounter) {
        this.wishListCounter = wishListCounter;
    }

    public Double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(Double averageRating) {
        this.averageRating = averageRating;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public String toString() {
        return "SellerProdDetailDto{" +
                "sellerProduct=" + sellerProduct +
                ", soldCounter=" + soldCounter +
                ", wishListCounter=" + wishListCounter +
                ", averageRating=" + averageRating +
                ", categoryId=" + categoryId +
                '}';
    }
}
