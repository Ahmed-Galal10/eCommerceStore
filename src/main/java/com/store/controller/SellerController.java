package com.store.controller;

import com.store.model.Seller;
import com.store.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/seller")
public class SellerController {


    private  SellerService sellerService;

    @Autowired
    public SellerController(SellerService sellerService){
        this.sellerService = sellerService;
    }

    @GetMapping
    public void getAllSellers(){

    }

    @PostMapping
    public void addSeller(){

    }

    @GetMapping("/{sellerId}")
    public  void getSeller(@PathVariable("sellerId") int sellerId){

    }

    @PutMapping("/{sellerId}")
    public  void updateSeller(@PathVariable("sellerId") int sellerId){

    }

    @DeleteMapping("/{sellerId}")
    public  void deleteSeller(@PathVariable("sellerId") int sellerId){

    }


    @GetMapping("/{sellerId}/products")
    public void getSellerProducts(@PathVariable("sellerId") int sellerId){

    }

    @GetMapping("/{sellerId}/sold-items")
    public void getSellerSoldItems(@PathVariable("sellerId") int sellerId){

    }

}
