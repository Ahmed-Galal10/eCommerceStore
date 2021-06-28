package com.store.dtos.product;

import java.util.List;

public class ProductWrapperDto {
    List<ProdDetailDto> products;
    Integer totalPages;
    Long totalElements;
    Double maxPrice;

    public List<ProdDetailDto> getProducts() {
        return products;
    }

    public void setProducts(List<ProdDetailDto> products) {
        this.products = products;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public Long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(Long totalElements) {
        this.totalElements = totalElements;
    }

    public Double getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(Double maxPrice) {
        this.maxPrice = maxPrice;
    }
}
