package com.store.controller;

import com.store.dtos.GenericResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/wishlist")
public class WishListController {


    @GetMapping
    public ResponseEntity<GenericResponse> getAllWishlists(){

        return null;
    }

    @PostMapping
    public ResponseEntity<GenericResponse> createWishlist(){

        return null;
    }

    @GetMapping("/{wishListId}")
    public  ResponseEntity< GenericResponse > getWishList(){
        return null;
    }


    @DeleteMapping("/{wishListId}")
    public  ResponseEntity< GenericResponse > deleteWishList(){
        return null;
    }


}
