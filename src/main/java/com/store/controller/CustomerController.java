package com.store.controller;


import com.store.dtos.GenericResponse;
import com.store.dtos.cart.CartDto;
import com.store.dtos.cart.CartItemDto;
import com.store.dtos.cart.CartItemRequest;
import com.store.dtos.customer.*;
import com.store.dtos.order.OrderDto;
import com.store.dtos.order.OrderRequest;
import com.store.service.CartService;
import com.store.service.CustomerService;
import com.store.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
@CrossOrigin
public class CustomerController {

    private CustomerService customerService;
    private CartService cartService;
    private OrderService orderService;
    @Autowired
    public  CustomerController(CustomerService customerService,
                               CartService cartService,
                               OrderService orderService){
        this.cartService = cartService;
        this.customerService = customerService;
        this.orderService = orderService;
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

    @PostMapping()
    public ResponseEntity<GenericResponse> addCustomer(@RequestBody CustomerRequestDto customerDto) {

        CustomerRequestDto customerRequestDto = customerService.addCustomer(customerDto);
        GenericResponse<CustomerRequestDto> response =
                new GenericResponse<>(customerRequestDto, HttpStatus.CREATED, "CUSTOMER CREATED");

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
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
    public ResponseEntity<GenericResponse< List<CustomerOrderDto> >> getCustomerOrders(@PathVariable("customerId") int customerId) {
        try {

            List<CustomerOrderDto> customerOrderDtoList = customerService.getCustomerOrders(customerId);
            GenericResponse< List<CustomerOrderDto> > resp =
                    new GenericResponse<>(customerOrderDtoList, HttpStatus.OK, "Request Success");
            return new ResponseEntity(resp, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/{customerId}/orders")
    public  ResponseEntity<GenericResponse> createOrder( @PathVariable("customerId") int customerId,
                                                         @RequestBody OrderRequest orderRequest){
        orderRequest.setCustomerId( customerId );
        OrderDto orderDto = orderService.createOrder(orderRequest);
        GenericResponse<OrderDto> response =
                new GenericResponse<>(orderDto, HttpStatus.CREATED, "ORDER CREATED");
        return  ResponseEntity.status(HttpStatus.CREATED).body(response);
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
