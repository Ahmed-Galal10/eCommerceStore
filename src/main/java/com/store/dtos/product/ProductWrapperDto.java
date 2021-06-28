package com.store.dtos.product;

import java.util.List;

public class ProductWrapperDto<T> {
    List<T> products;
    Integer totalPages;
    Long totalElements;
    Double maxPrice;

    public List<T> getProducts() {
        return products;
    }

    public void setProducts(List<T> products) {
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
