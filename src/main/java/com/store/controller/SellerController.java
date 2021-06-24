package com.store.controller;

import com.store.dtos.GenericResponse;
import com.store.dtos.product.SellerProdDetailDto;
import com.store.dtos.product.SellerProductRequestDto;
import com.store.dtos.seller.SellerDto;
import com.store.dtos.seller.SellerProductDto;
import com.store.dtos.seller.SellerRequest;
import com.store.dtos.seller.SellerRequestDto;
import com.store.service.ProductService;
import com.store.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<GenericResponse<?>> getAllSellers() {

        List<SellerDto> sellerDtos = sellerService.getAll();

        GenericResponse<List<SellerDto>> response =
                new GenericResponse<>(sellerDtos, HttpStatus.OK, "REQUEST SUCCESSFUL");

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


    @PostMapping()
    public ResponseEntity<GenericResponse> addSeller(@RequestBody SellerRequestDto sellerDto) {

        SellerRequestDto sellerRequestDto = sellerService.addSeller(sellerDto);
        GenericResponse<SellerRequestDto> response =
                new GenericResponse<>(sellerRequestDto, HttpStatus.CREATED, "SELLER CREATED");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/{sellerId}")
    public GenericResponse<SellerDto> getSeller(@PathVariable("sellerId") int sellerId) {

        SellerDto dto = sellerService.getBySellerId(sellerId);

        GenericResponse<SellerDto> response =
                new GenericResponse<>(dto, HttpStatus.OK, "REQUEST SUCCESSFUL");

        return response;

    }

    @PutMapping("/{sellerId}")
    public GenericResponse<SellerDto> updateSeller(@PathVariable("sellerId") int sellerId,
                                                   @RequestBody SellerRequest sellerRequest) {

        SellerDto dto = sellerService.updateSeller(sellerId, sellerRequest);

        GenericResponse<SellerDto> response =
                new GenericResponse<>(dto, HttpStatus.OK, "REQUEST SUCCESSFUL");

        return response;

    }

    @DeleteMapping("/{sellerId}")
    public GenericResponse<SellerDto> deleteSeller(@PathVariable("sellerId") int sellerId) {

        SellerDto dto = sellerService.deleteById(sellerId);

        GenericResponse<SellerDto> response =
                new GenericResponse<>(dto, HttpStatus.OK, "REQUEST SUCCESSFUL");


        return response;
    }


    @GetMapping(value = "/{sellerId}/products", params = {"page", "size"})
    public GenericResponse<List<SellerProductDto>>
    getSellerProducts(@PathVariable("sellerId") int sellerId,
                      @RequestParam(value = "page", defaultValue = "0") int page,
                      @RequestParam(value = "size", defaultValue = "1") int size) {


        Pageable pageable = PageRequest.of(page, size);
        System.out.println(page + size);
        List<SellerProductDto> dtos = productService.getProductsByUserId(sellerId, pageable);
        System.out.println(dtos);
        GenericResponse<List<SellerProductDto>> response =
                new GenericResponse<>(dtos, HttpStatus.OK, "REQUEST SUCCESSFUL");

        return response;
    }

    @GetMapping("/{sellerId}/sold-items")
    public void getSellerSoldItems(@PathVariable("sellerId") int sellerId){

    }


    @GetMapping(value = "/products/{productId}")
    public ResponseEntity<GenericResponse> getSellerProductById(@PathVariable("productId") Integer productId) {
        try {
            SellerProdDetailDto sellerProdDetailDto = productService.getSellerProductDetailById(productId);

            GenericResponse<SellerProdDetailDto> response =
                   new GenericResponse(sellerProdDetailDto, HttpStatus.OK, "REQUEST_SUCCESS");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.ok(new GenericResponse<>(null, HttpStatus.BAD_REQUEST, e.getMessage()));
        }
    }

    @PutMapping("/products/{productId}")
    public ResponseEntity<GenericResponse> updateSellerProduct(@PathVariable("productId") Integer productId,
                                                               @RequestBody SellerProductRequestDto sellerProductDto)
    {
        try {
            sellerProductDto.setId(productId);
            sellerService.updateSellerProduct(sellerProductDto);
            GenericResponse<SellerProductRequestDto> response =
                    new GenericResponse<>(sellerProductDto, HttpStatus.NO_CONTENT, "Product UPDATED");

            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok(new GenericResponse<>(null, HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage()));
        }
    }

}
