package com.store.controller;

import com.store.dtos.GenericResponse;
import com.store.dtos.product.PagingResponse;
import com.store.dtos.product.ProductWrapperDto;
import com.store.dtos.product.SellerProdDetailDto;
import com.store.dtos.product.SellerProductRequestDto;
import com.store.dtos.seller.SellerDto;
import com.store.dtos.seller.SellerProductDto;
import com.store.dtos.seller.SellerRequest;
import com.store.dtos.seller.SellerRequestDto;
import com.store.dtos.solditem.SoldItemDto;
import com.store.service.OrderService;
import com.store.service.ProductService;
import com.store.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/seller")
@CrossOrigin
public class SellerController {

    private final SellerService sellerService;
    private final ProductService productService;
    private final OrderService orderService;

    @Autowired
    public SellerController(SellerService sellerService, ProductService productService,
                            OrderService orderService) {

        this.sellerService = sellerService;
        this.productService = productService;
        this.orderService = orderService;
    }

    @GetMapping
    public ResponseEntity<GenericResponse<?>> getAllSellers() {

        List<SellerDto> sellerDtos = sellerService.getAll();

        GenericResponse<List<SellerDto>> response =
                new GenericResponse<>(sellerDtos, HttpStatus.OK, "REQUEST SUCCESSFUL");

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


    @PostMapping
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
    public ResponseEntity<PagingResponse<List<SellerProductDto>>>
    getSellerProducts(@PathVariable("sellerId") int sellerId,
                      @RequestParam(value = "page", defaultValue = "0") int page,
                      @RequestParam(value = "size", defaultValue = "1") int size) {


        Pageable pageable = PageRequest.of(page, size);
//        System.out.println(page + size);
        ProductWrapperDto<SellerProductDto> wrapperProduct = productService.getProductsByUserId(sellerId, pageable);
        if (wrapperProduct != null) {
            List<SellerProductDto> dtos = wrapperProduct.getProducts();

            Integer totalpages = wrapperProduct.getTotalPages();

            Long totalElements = wrapperProduct.getTotalElements();

            System.out.println(dtos);

            PagingResponse<List<SellerProductDto>> response = new PagingResponse<>(dtos, HttpStatus.OK, "Found");

            response.setTotalElements(totalElements);

            response.setTotalPages(totalpages);

            return ResponseEntity.status(HttpStatus.OK).body(response);

        } else {

            List<SellerProductDto> dtos = new ArrayList<>();

            Integer totalpages = 0;

            Long totalElements = 0l;


            PagingResponse<List<SellerProductDto>> response = new PagingResponse<>(dtos, HttpStatus.NOT_FOUND, "NotFound");

            response.setTotalElements(totalElements);

            response.setTotalPages(totalpages);

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);


        }


    }


    @GetMapping("/{sellerId}/sold-items")
    public ResponseEntity<GenericResponse<?>> getSellerSoldItems(@PathVariable("sellerId") int sellerId){

        try {
            List<SoldItemDto> soldItems =  orderService.getSoldItemsBySeller(sellerId);
            GenericResponse<SellerProdDetailDto> response =
                    new GenericResponse(soldItems, HttpStatus.OK, "REQUEST_SUCCESS");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new GenericResponse<>(null, HttpStatus.BAD_REQUEST, e.getMessage()));
        }
    }


    @GetMapping(value = "/products/{productId}")
    public ResponseEntity<GenericResponse> getSellerProductById(@PathVariable("productId") Integer productId) {
        try {
            SellerProdDetailDto sellerProdDetailDto = productService.getSellerProductDetailById(productId);

            GenericResponse<SellerProdDetailDto> response =
                   new GenericResponse(sellerProdDetailDto, HttpStatus.OK, "REQUEST_SUCCESS");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new GenericResponse<>(null, HttpStatus.BAD_REQUEST, e.getMessage()));
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
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new GenericResponse<>(null, HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage()));
        }
    }

    @PutMapping(value = "/products/{productId}",params = "sale")
    public ResponseEntity<GenericResponse> updateSellerProductSaleState(@PathVariable("productId") Integer productId,
                                                               @RequestBody SellerProductRequestDto sellerProductDto)
    {
        try {
            sellerProductDto.setId(productId);
            sellerService.updateSellerProductSale(sellerProductDto);
            GenericResponse<SellerProductRequestDto> response =
                    new GenericResponse<>(sellerProductDto, HttpStatus.NO_CONTENT, "Product UPDATED");

            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new GenericResponse<>(null, HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage()));
        }
    }





}
