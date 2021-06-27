package com.store.dtos.product;

import com.store.dtos.GenericResponse;
import org.springframework.http.HttpStatus;

public class PagingResponse<T> extends GenericResponse<T> {
    Integer totalPages;
    Long totalElements;
    Double maxPrice;

    public PagingResponse(T data, HttpStatus httpStatus, String message) {
        super(data, httpStatus, message);
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
