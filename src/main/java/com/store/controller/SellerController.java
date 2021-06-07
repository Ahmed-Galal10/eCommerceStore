package com.store.controller;

import com.store.dtos.seller.ProductDto;
import com.store.dtos.seller.SellerDto;
import com.store.dtos.seller.SellerRequest;
import com.store.service.ProductService;
import com.store.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/seller")
public class SellerController {


    private final SellerService sellerService;
    private final ProductService productService;

    @Autowired
    public SellerController(SellerService sellerService, ProductService productService) {
        this.sellerService = sellerService;
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<SellerDto>> getAllSellers() {
        return ResponseEntity.ok(sellerService.getAll());
    }

    @PostMapping
    public ResponseEntity<SellerDto> addSeller(@RequestBody SellerRequest sellerRequest) {
        return ResponseEntity.ok(sellerService.addSeller(sellerRequest));
    }

    @GetMapping("/{sellerId}")
    public ResponseEntity<SellerDto> getSeller(@PathVariable("sellerId") int sellerId) {
        return ResponseEntity.ok(sellerService.getBySellerId(sellerId));
    }

    @PutMapping("/{sellerId}")
    public ResponseEntity<SellerDto> updateSeller(@PathVariable("sellerId") int sellerId, @RequestBody SellerRequest sellerRequest) {
        return ResponseEntity.ok(sellerService.updateSeller(sellerId, sellerRequest));

    }

    @DeleteMapping("/{sellerId}")
    public ResponseEntity<SellerDto> deleteSeller(@PathVariable("sellerId") int sellerId) {

        return ResponseEntity.ok(sellerService.deleteById(sellerId));
    }


    @GetMapping("/{sellerId}/products")
    public ResponseEntity<List<ProductDto>> getSellerProducts(@PathVariable("sellerId") int sellerId) {
        return ResponseEntity.ok(productService.getProductsByUserId(sellerId));
    }

    @GetMapping("/{sellerId}/sold-items")
    public void getSellerSoldItems(@PathVariable("sellerId") int sellerId){

    }

}
