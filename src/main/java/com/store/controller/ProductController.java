package com.store.controller;

import com.store.dtos.product.ProdDetailDto;
import com.store.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<ProdDetailDto>> findAllProducts() {

        List<ProdDetailDto> allProducts = productService.getAllProducts();

        return ResponseEntity.ok(allProducts);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    public ResponseEntity<ProdDetailDto> findProductById(@PathVariable Integer id) {
        try {
            ProdDetailDto prodDetailDto = productService.getProductById(id);
            return ResponseEntity.ok(prodDetailDto);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<ProdDetailDto> createProduct(@RequestBody ProdDetailDto prodDetailDto) {
        prodDetailDto = productService.addProduct(prodDetailDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(prodDetailDto);
    }

    @RequestMapping(method = RequestMethod.PUT,path="/{id}")
    public ResponseEntity<ProdDetailDto> updateProduct(ProdDetailDto prodDetailDto,@PathVariable Integer id) {
        prodDetailDto = productService.updateProduct(id,prodDetailDto);

        return ResponseEntity.status(HttpStatus.OK).body(prodDetailDto);
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/{id}")
    public ResponseEntity deleteProduct(@PathVariable("id") Integer id) {
        productService.deleteProduct(id);

        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
