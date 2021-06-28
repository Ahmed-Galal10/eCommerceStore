package com.store.controller;

import com.store.dtos.GenericResponse;
import com.store.dtos.product.*;
import com.store.dtos.review.ReviewDto;
import com.store.dtos.solditem.SoldItemDto;
import com.store.repository.OrderItemRepo;

import com.store.service.OrderService;
import com.store.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
@CrossOrigin
public class ProductController {

    ProductService productService;
    OrderService orderService;

    @Autowired
    public ProductController(ProductService productService,
                             OrderService orderService) {
        this.productService = productService;
        this.orderService = orderService;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    public ResponseEntity<GenericResponse<ProdDetailDto>> findProductById(@PathVariable Integer id) {

        try{
        ProdDetailDto prodDetailDto = productService.getProductById(id);

        GenericResponse<ProdDetailDto> response = new GenericResponse(prodDetailDto, HttpStatus.OK, "Found");

            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            GenericResponse<ProdDetailDto> response = new GenericResponse(null, HttpStatus.INTERNAL_SERVER_ERROR, "Not Found");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<GenericResponse> createProduct(@RequestBody ProdDetailDto prodDetailDto) {

        System.out.println(prodDetailDto);
        prodDetailDto = productService.addOrUpdateProduct(prodDetailDto);

        GenericResponse<ProdDetailDto> response =
                new GenericResponse<>(prodDetailDto, HttpStatus.CREATED, "PRODUCT CREATED");

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
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

    @GetMapping("/{id}/prodsSoldData")
    public ResponseEntity<GenericResponse<?>> prodQtySoldVsTime(@PathVariable("id") Integer prodId) {
        try {

            List<ProdSoldData> data = orderService.getProdSoldData(prodId);
            GenericResponse<List<ProdSoldData>> response =
                    new GenericResponse<>(data, HttpStatus.OK, "REQUEST SUCCESSFUL");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            GenericResponse<List<ProdSoldData>> response =
                    new GenericResponse<>(null, HttpStatus.OK, "REQUEST SUCCESSFUL");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @GetMapping("/sold-items")
    public ResponseEntity<GenericResponse<?>> getAllSoldItems(){

        try {
            List<SoldItemDto> soldItems =  orderService.getAllSoldItems();
            GenericResponse<SellerProdDetailDto> response =
                    new GenericResponse(soldItems, HttpStatus.OK, "REQUEST_SUCCESS");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new GenericResponse<>(null, HttpStatus.BAD_REQUEST, e.getMessage()));
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

    @GetMapping("/count")
    public  ResponseEntity<GenericResponse> getProductsCount(){

        Long count = productService.getProductsCount();

        GenericResponse<Long> response =
                new GenericResponse(count, HttpStatus.OK, "REQUEST SUCCESS");

        return ResponseEntity.ok(response);
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

        ProductWrapperDto<ProdDetailDto> productWrapperDto =
                productService.getAllProductsByFilters(pageNumber, pageSize, priceMin, priceMax, subCategoriesIds, name);

        // get data
        if (productWrapperDto != null) {
            List<ProdDetailDto> products = productWrapperDto.getProducts();
            Integer totalPages = productWrapperDto.getTotalPages();
            Long totalElements = productWrapperDto.getTotalElements();
            Double maxPrice = productWrapperDto.getMaxPrice();

            PagingResponse<ProdDetailDto> response = new PagingResponse(products, HttpStatus.OK, "Found");
            response.setTotalPages(totalPages);
            response.setTotalElements(totalElements);
            response.setMaxPrice(maxPrice);

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
    public ResponseEntity<PagingResponse<List<ReviewDto>>> getProductReviews(
            @PathVariable("id") Integer id,
            @RequestParam(value = "page", required = false, defaultValue = "0") Integer pageNumber) {

        try {
            List<ReviewDto> productReviews = productService.getProductReviews(id);

            PagingResponse<List<ReviewDto>> response = new PagingResponse<>(productReviews, HttpStatus.OK, "Found");
            response.setTotalElements((long) productReviews.size());

            return ResponseEntity.status(HttpStatus.OK).body(response);
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

    @RequestMapping(method = RequestMethod.POST, path = "/{id}/reviews")
    public ResponseEntity<GenericResponse<ReviewDto>> addProductReview(@PathVariable("id") Integer id, @RequestBody ReviewDto reviewDto) {
        ReviewDto review = productService.addReview(id, reviewDto);

        GenericResponse<ReviewDto> response = new GenericResponse(review, HttpStatus.OK, "Review successfully added");

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
