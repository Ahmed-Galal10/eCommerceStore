package com.store.controller;

import com.store.dtos.GenericResponse;
import com.store.dtos.product.ProdDetailDto;
import com.store.dtos.product.ProductImagesDto;
import com.store.dtos.product.SellerProdDetailDto;
import com.store.dtos.review.ReviewDto;
import com.store.model.Product;
import com.store.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/products")
public class ProductController {

    ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
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

////    @GetMapping(value = "/{productId}", params = {"meta"})
//    @GetMapping(value = "/{productId}")
//    public ResponseEntity<GenericResponse> getSellerProductById(@PathVariable("productId") Integer productId) {
//        try {
//            SellerProdDetailDto sellerProdDetailDto = productService.getSellerProductDetailById(productId);
//
//            GenericResponse<SellerProdDetailDto> response =
//                   new GenericResponse(sellerProdDetailDto, HttpStatus.OK, "REQUEST_SUCCESS");
//            return ResponseEntity.ok(response);
//        } catch (Exception e) {
//            return ResponseEntity.ok(new GenericResponse<>(null, HttpStatus.BAD_REQUEST, e.getMessage()));
//        }
//    }

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
    public ResponseEntity<List<ProdDetailDto>> findAllProducts(
            @RequestParam(value = "page", required = false, defaultValue = "0") Integer pageNumber,
            @RequestParam(value = "priceMin", required = false, defaultValue = "0.0") Double priceMin,
            @RequestParam(value = "priceMax", required = false, defaultValue = "1000000.0") Double priceMax,
            @RequestParam(value = "cat", required = false) List<Integer> subCategoriesIds,
            @RequestParam(value = "name", required = false, defaultValue = "%") String name
    ) {

        List<ProdDetailDto> products =
                productService.getAllProductsByFilters(pageNumber, priceMin, priceMax, subCategoriesIds, name);

        return ResponseEntity.ok(products);
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
