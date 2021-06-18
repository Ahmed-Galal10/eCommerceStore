package com.store.dtos.product;


public class SellerProdDetailDto {

    ProdDetailDto prodDetailDto = new ProdDetailDto();
    Integer soldCounter;
    Integer wishListCounter;
    Double averageRating;

    public SellerProdDetailDto() {
    }

    public ProdDetailDto getProdDetailDto() {
        return prodDetailDto;
    }

    public void setProdDetailDto(ProdDetailDto prodDetailDto) {
        this.prodDetailDto = prodDetailDto;
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

    @Override
    public String toString() {
        return "SellerProdDetailDto{" +
                "soldCounter=" + soldCounter +
                ", wishListCounter=" + wishListCounter +
                ", averageRating=" + averageRating +
                '}';
    }
}
