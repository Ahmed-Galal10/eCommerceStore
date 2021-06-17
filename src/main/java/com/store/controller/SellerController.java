package com.store.controller;

import com.store.dtos.seller.SellerDto;
import com.store.dtos.seller.SellerProductDto;
import com.store.dtos.seller.SellerRequest;
import com.store.service.ProductService;
import com.store.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/seller")
@CrossOrigin
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

        List<SellerDto> sellerDtos = sellerService.getAll();

        return ResponseEntity.ok(sellerDtos);
    }


    @PostMapping
    public ResponseEntity<SellerDto> addSeller(@RequestBody SellerRequest sellerRequest) {

        SellerDto dto = sellerService.addSeller(sellerRequest);

        return ResponseEntity.ok(dto);

    }

    @GetMapping("/{sellerId}")
    public ResponseEntity<SellerDto> getSeller(@PathVariable("sellerId") int sellerId) {

        SellerDto dto = sellerService.getBySellerId(sellerId);

        return ResponseEntity.ok(dto);

    }

    @PutMapping("/{sellerId}")
    public ResponseEntity<SellerDto> updateSeller(@PathVariable("sellerId") int sellerId,
                                                  @RequestBody SellerRequest sellerRequest) {

        SellerDto dto = sellerService.updateSeller(sellerId, sellerRequest);

        return ResponseEntity.ok(dto);

    }

    @DeleteMapping("/{sellerId}")
    public ResponseEntity<SellerDto> deleteSeller(@PathVariable("sellerId") int sellerId) {

        SellerDto dto = sellerService.deleteById(sellerId);

        return ResponseEntity.ok(dto);
    }


    @GetMapping("/{sellerId}/products")
    public ResponseEntity<List<SellerProductDto>> getSellerProducts(@PathVariable("sellerId") int sellerId) {
        return ResponseEntity.ok(productService.getProductsByUserId(sellerId));
    }

    @GetMapping("/{sellerId}/sold-items")
    public void getSellerSoldItems(@PathVariable("sellerId") int sellerId){

    }

}
