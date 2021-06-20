package com.store.controller;

import com.store.dtos.GenericResponse;
import com.store.dtos.product.PagingResponse;
import com.store.dtos.product.ProdDetailDto;
import com.store.dtos.product.ProductImagesDto;
import com.store.dtos.product.ProductWrapperDto;
import com.store.dtos.review.ReviewDto;
import com.store.model.Product;
import com.store.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/products")
public class ProductController {

    ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    public ResponseEntity<GenericResponse<ProdDetailDto>> findProductById(@PathVariable Integer id) {
        try {
            ProdDetailDto prodDetailDto = productService.getProductById(id);

            GenericResponse<ProdDetailDto> response = new GenericResponse(prodDetailDto, HttpStatus.OK, "Found");

            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            return null;
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<ProdDetailDto> createProduct(@RequestBody ProdDetailDto prodDetailDto) {
        prodDetailDto = productService.addOrUpdateProduct(prodDetailDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(prodDetailDto);
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/{id}")
    public ResponseEntity<ProdDetailDto> updateProduct(@RequestBody ProdDetailDto prodDetailDto, @PathVariable("id") Integer id) {

        try {
            productService.getProductById(id);

            prodDetailDto.setId(id);

            productService.addOrUpdateProduct(prodDetailDto);

            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/{id}")
    public ResponseEntity deleteProduct(@PathVariable("id") Integer id) {
        try {
            productService.getProductById(id);

            productService.deleteProduct(id);

            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<PagingResponse> findAllProducts(
            @RequestParam(value = "page", required = false, defaultValue = "0") Integer pageNumber,
            @RequestParam(value = "pageSize", required = false, defaultValue = "9") Integer pageSize,
            @RequestParam(value = "priceMin", required = false, defaultValue = "0.0") Double priceMin,
            @RequestParam(value = "priceMax", required = false, defaultValue = "1000000.0") Double priceMax,
            @RequestParam(value = "cat", required = false) List<Integer> subCategoriesIds,
            @RequestParam(value = "name", required = false, defaultValue = "%") String name
    ) {

        ProductWrapperDto productWrapperDto =
                productService.getAllProductsByFilters(pageNumber, pageSize, priceMin, priceMax, subCategoriesIds, name);

        // get data
        if (productWrapperDto != null) {
            List<ProdDetailDto> products = productWrapperDto.getProducts();
            Integer totalPages = productWrapperDto.getTotalPages();
            Long totalElements = productWrapperDto.getTotalElements();

            PagingResponse<ProdDetailDto> response = new PagingResponse(products, HttpStatus.OK, "Found");
            response.setTotalPages(totalPages);
            response.setTotalElements(totalElements);

            return ResponseEntity.status(HttpStatus.OK).body(response);
        } else {
            List<ProdDetailDto> products = new ArrayList<>();

            PagingResponse<ProdDetailDto> response = new PagingResponse(products, HttpStatus.OK, "Not_Found");
            response.setTotalPages(0);
            response.setTotalElements(0L);

            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{id}/reviews")
    public ResponseEntity<List<ReviewDto>> getProductReviews(
            @PathVariable("id") Integer id,
            @RequestParam(value = "page", required = false, defaultValue = "0") Integer pageNumber) {

        try {
            List<ReviewDto> productReviews = productService.getProductReviews(id, pageNumber);

            return ResponseEntity.ok(productReviews);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @RequestMapping(method = RequestMethod.POST, path = "/{id}/images")
    public ResponseEntity<ProductImagesDto> addProductImageToProduct(
            @RequestBody ProductImagesDto productImagesDto,
            @PathVariable("id") Integer productId) {

        try {
            productImagesDto = productService.addImageToProduct(productImagesDto, productId);

            return ResponseEntity.ok(productImagesDto);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }

    }
}
