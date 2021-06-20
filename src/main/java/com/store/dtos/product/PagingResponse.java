package com.store.dtos.product;

import com.store.dtos.GenericResponse;
import org.springframework.http.HttpStatus;

public class PagingResponse<T> extends GenericResponse<T> {
    Integer totalPages;
    Long totalElements;

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
}
