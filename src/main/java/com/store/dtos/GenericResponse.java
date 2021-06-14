package com.store.dtos;

import org.springframework.http.HttpStatus;

public class GenericResponse<T> {

   private T data;
   private HttpStatus httpStatus;
   private int httpCode;
   private String message;

    public GenericResponse(T data, HttpStatus httpStatus, String message) {
        this.data = data;
        this.httpStatus = httpStatus;
        this.message = message;
        httpCode = httpStatus.value();
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getHttpCode() {
        return httpCode;
    }

    public void setHttpCode(int httpCode) {
        this.httpCode = httpCode;
    }
}
