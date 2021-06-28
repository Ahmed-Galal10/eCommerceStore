package com.store.controller;

import com.store.dtos.GenericResponse;
import com.store.exceptions.CartException;

import com.store.exceptions.OrderException;

import com.store.exceptions.ReviewException;
import com.store.exceptions.ShopException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(CartException.class)
    public ResponseEntity<GenericResponse> handleCartException(CartException e) {
        GenericResponse response =
                new GenericResponse(null, HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(OrderException.class)
    public ResponseEntity<GenericResponse> handleOrderException(OrderException e) {
        GenericResponse response =
                new GenericResponse(null, HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(ReviewException.class)
    public ResponseEntity<GenericResponse> handleCartException(ReviewException e) {
        GenericResponse response =
                new GenericResponse(null, HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(ShopException.class)
    public ResponseEntity<GenericResponse> handleCartException(ShopException e) {
        GenericResponse response =
                new GenericResponse(null, HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

}
