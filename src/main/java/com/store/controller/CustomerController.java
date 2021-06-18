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
import net.bytebuddy.description.type.TypeList;
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
    public ResponseEntity<GenericResponse<CustomerDto>> getCustomerById(@PathVariable("customerId") int customerId) {
        GenericResponse<CustomerDto> customerGenericResponse = null;
        try {
            CustomerDto customerDto = customerService.getCustomerById(customerId);
             customerGenericResponse =
                    new GenericResponse<>(customerDto,HttpStatus.OK,"User Retrieved Successfully");
            return new ResponseEntity<GenericResponse<CustomerDto>>(customerGenericResponse, HttpStatus.OK);
        } catch (Exception e) {
            customerGenericResponse =
                    new GenericResponse<>(null,HttpStatus.NOT_FOUND,"Can't Found The User");
            return new ResponseEntity<GenericResponse<CustomerDto>>(customerGenericResponse, HttpStatus.OK);
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

    @PutMapping("/{customerId}")
    public ResponseEntity<GenericResponse> updateCustomer(@PathVariable("customerId") int customerId,
                                                          @RequestBody CustomerDto customerDto) {
        try {
            customerDto.setId(customerId);
            customerService.updateCustomer(customerDto);

            GenericResponse<CustomerDto> response =
                    new GenericResponse<>(customerDto, HttpStatus.NO_CONTENT, "CUSTOMER UPDATED");

            return ResponseEntity.status(HttpStatus.OK).body(response);

        } catch (Exception e) {
            return ResponseEntity.ok(new GenericResponse<>(null, HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage()));
        }

    }
    @PatchMapping("/{customerId}")
    public ResponseEntity<GenericResponse<CustomerDto>> updateCustomerImage(@PathVariable("customerId") int customerId,
                                                                            @RequestBody String imageUrl){
        try {
            CustomerDto customerDto = customerService.getCustomerById(customerId);
            customerDto.setImage(imageUrl);
            customerService.updateCustomer(customerDto);
            GenericResponse<CustomerDto> response =
                    new GenericResponse<>(customerDto, HttpStatus.NO_CONTENT, "CUSTOMER IMAGE UPDATED");
            return ResponseEntity.status(HttpStatus.OK).body(response);

        }catch (Exception e){
            return ResponseEntity.ok(new GenericResponse<>(null, HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage()));
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
    public  ResponseEntity<GenericResponse> getCart(@PathVariable("cusomterId") Integer userId){
        CartDto cartDto =  cartService.getCartByUserId(userId);

        GenericResponse<CartDto> response =
                new GenericResponse(cartDto, HttpStatus.OK, "REQUEST_SUCCESS");

        return ResponseEntity.ok(response);
    }

    @PostMapping(path = "/{cusomterId}/carts")
    public ResponseEntity<GenericResponse> addCartItem(@PathVariable("cusomterId") Integer userId,
                                                   @RequestBody CartItemRequest cartItemRequest) {
        cartItemRequest.setCustomerId(userId);
        CartItemDto cartItemDto =  cartService.addCartItem(cartItemRequest);

        GenericResponse<CartItemDto> response =
                new GenericResponse(cartItemDto, HttpStatus.OK, "REQUEST_SUCCESS");

        return ResponseEntity.status(HttpStatus.CREATED).body(response) ;
    }

    @PutMapping(path = "/{cusomterId}/carts")
    public ResponseEntity<GenericResponse> updateCartItem(@PathVariable("cusomterId") Integer userId,
                                                      @RequestBody CartItemRequest cartItemRequest) {
        cartItemRequest.setCustomerId(userId);
        CartItemDto cartItemDto =  cartService.updateCartItem(cartItemRequest);

        GenericResponse<CartItemDto> response =
                new GenericResponse(cartItemDto, HttpStatus.OK, "REQUEST_SUCCESS");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping(path = "/{cusomterId}/carts")
    public ResponseEntity<GenericResponse> deleteCartItem(@PathVariable("cusomterId") Integer userId,
                                                  @RequestBody CartItemRequest cartItemRequest) {
        cartItemRequest.setCustomerId(userId);
        boolean isDeleted =  cartService.deleteCartItem(cartItemRequest);
        GenericResponse<Boolean> response =
                new GenericResponse(isDeleted, HttpStatus.OK, "REQUEST_SUCCESS");
        return ResponseEntity.ok(response);
    }

}
