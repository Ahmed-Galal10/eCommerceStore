package com.store.controller;


import com.store.dtos.cart.CartDto;
import com.store.dtos.cart.CartItemDto;
import com.store.dtos.cart.CartItemRequest;
import com.store.dtos.customer.*;
import com.store.service.CartService;
import com.store.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private CustomerService customerService;
    private CartService cartService;

    @Autowired
    public  CustomerController(CustomerService customerService,
                               CartService cartService){
        this.cartService = cartService;
        this.customerService = customerService;
    }

    @GetMapping("")
    public ResponseEntity<List<CustomerDto>> getAllCustomers() {

        try {
            System.out.println("plaaaaaa");
            return new ResponseEntity<>(customerService.getAllCustomers(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("not plaaaaaa");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/hi")
    public String test(){
        return "eshta ".repeat(5);
    }

    @PostMapping()
    public ResponseEntity<String> addCustomer(@RequestBody CustomerDto customerDto) {
        try {
            customerService.addCustomer(customerDto);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest()
                    .body("Couldn't add customer");
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<CustomerDto> getCustomerById(@PathVariable("customerId") int customerId) {
        try {
            CustomerDto customerDto = customerService.getCustomerById(customerId);
            return new ResponseEntity<>(customerDto, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{customerId}")
    public ResponseEntity<String> deleteCustomer(@PathVariable("customerId") int customerId) {
        try {
            customerService.deleteCustomer(customerId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping()
    public ResponseEntity<String> updateCustomer(@RequestBody CustomerDto customerDto) {
        try {

            customerService.updateCustomer(customerDto);

            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/{customerId}/orders")
    public ResponseEntity<List<CustomerOrderDto>> getCustomerOrders(@PathVariable("customerId") int customerId) {
        try {
            List<CustomerOrderDto> customerOrderDtoList = customerService.getCustomerOrders(customerId);
            return new ResponseEntity<>(customerOrderDtoList, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{customerId}/reviews")
    public ResponseEntity<List<CustomerReviewDto>> getCustomerReviews(@PathVariable("customerId") int customerId) {
        try {
            List<CustomerReviewDto> customerReviewDtoList = customerService.getCustomerReviews(customerId);

            return new ResponseEntity<>(customerReviewDtoList, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{customerId}/wishlist")
    public ResponseEntity<CustomerWishListDto> getCustomerWishList(@PathVariable("customerId") int customerId) {
        try {
            CustomerWishListDto customerWishListDto = customerService.getCustomerWishList(customerId);

            return new ResponseEntity<>(customerWishListDto, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    //=================================== Cart Api ==================================


    //TODO ====> Ask About UserId
    @GetMapping(path = "/{cusomterId}/carts")
    public  ResponseEntity<CartDto> getCart(@PathVariable("cusomterId") Integer userId){

        CartDto cartDto =  cartService.getCartByUserId(userId);
        return ResponseEntity.ok(cartDto);
    }

    @PostMapping(path = "/{cusomterId}/carts")
    public ResponseEntity<CartItemDto> addCartItem(@PathVariable("cusomterId") Integer userId,
                                                   @RequestBody CartItemRequest cartItemRequest) {
        cartItemRequest.setCustomerId(userId);
        CartItemDto cartItemDto =  cartService.addCartItem(cartItemRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(cartItemDto) ;
    }

    @PutMapping(path = "/{cusomterId}/carts")
    public ResponseEntity<CartItemDto> updateCartItem(@PathVariable("cusomterId") Integer userId,
                                                      @RequestBody CartItemRequest cartItemRequest) {
        cartItemRequest.setCustomerId(userId);
        CartItemDto cartItemDto =  cartService.updateCartItem(cartItemRequest);
        return ResponseEntity.ok(cartItemDto);
    }

    @DeleteMapping(path = "/{cusomterId}/carts")
    public ResponseEntity<Boolean> deleteCartItem(@PathVariable("cusomterId") Integer userId,
                                                  @RequestBody CartItemRequest cartItemRequest) {
        cartItemRequest.setCustomerId(userId);
        boolean isDeleted =  cartService.deleteCartItem(cartItemRequest);
        return ResponseEntity.ok(true);
    }

}
