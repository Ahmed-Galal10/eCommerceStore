package com.store.controller;


import com.store.dtos.GenericResponse;
import com.store.dtos.cart.CartDto;
import com.store.dtos.cart.CartItemDto;
import com.store.dtos.cart.CartItemRequest;
import com.store.dtos.checkout.CheckoutStateDto;
import com.store.dtos.checkout.PaymentInfoDto;
import com.store.dtos.customer.*;
import com.store.dtos.order.OrderDto;
import com.store.dtos.order.OrderRequest;
import com.store.dtos.payment.CreditCardDto;
import com.store.dtos.wishlist.WishlistProdRequest;
import com.store.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
@CrossOrigin
public class CustomerController {

    private CustomerService customerService;
    private CartService cartService;
    private OrderService orderService;
    private WishListService wishListService;
    private CheckoutService checkoutService;

    @Autowired
    public CustomerController(CustomerService customerService,
                              CartService cartService,
                              OrderService orderService,
                              WishListService wishListService, CheckoutService checkoutService) {
        this.cartService = cartService;
        this.customerService = customerService;
        this.orderService = orderService;
        this.wishListService = wishListService;
        this.checkoutService = checkoutService;
    }

    @GetMapping("")
    public ResponseEntity<GenericResponse<List<CustomerDto>>> getAllCustomers() {
        try {
            List<CustomerDto> customerDto = customerService.getAllCustomers();
            GenericResponse<List<CustomerDto>> response =
                    new GenericResponse<>(customerDto, HttpStatus.OK, "REQUEST SUCCESSFUL");
            return  ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok(new GenericResponse<>(null, HttpStatus.BAD_REQUEST, e.getMessage()));
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
                    new GenericResponse<>(customerDto, HttpStatus.OK, "User Retrieved Successfully");
            return new ResponseEntity<GenericResponse<CustomerDto>>(customerGenericResponse, HttpStatus.OK);
        } catch (Exception e) {
            customerGenericResponse =
                    new GenericResponse<>(null, HttpStatus.NOT_FOUND, "Can't Found The User");
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
                                                                            @RequestBody CustomerImageDto customerImageDto) {
        try {
            CustomerDto customerDto = customerService.getCustomerById(customerId);
            customerDto.setImage(customerImageDto.getImage());
            customerService.updateCustomer(customerDto);
            GenericResponse<CustomerDto> response =
                    new GenericResponse<>(customerDto, HttpStatus.NO_CONTENT, "CUSTOMER IMAGE UPDATED");
            return ResponseEntity.status(HttpStatus.OK).body(response);

        } catch (Exception e) {
            return ResponseEntity.ok(new GenericResponse<>(null, HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage()));
        }
    }

    @GetMapping("/{customerId}/orders")
    public ResponseEntity<GenericResponse<List<CustomerOrderDto>>> getCustomerOrders(@PathVariable("customerId") int customerId) {
        try {

            List<CustomerOrderDto> customerOrderDtoList = customerService.getCustomerOrders(customerId);
            GenericResponse<List<CustomerOrderDto>> resp =
                    new GenericResponse<>(customerOrderDtoList, HttpStatus.OK, "Request Success");
            return new ResponseEntity(resp, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/{customerId}/orders")
    public ResponseEntity<GenericResponse> createOrder(@PathVariable("customerId") int customerId,
                                                       @RequestBody OrderRequest orderRequest) {
        orderRequest.setCustomerId(customerId);
        OrderDto orderDto = orderService.createOrder(orderRequest);
        GenericResponse<OrderDto> response =
                new GenericResponse<>(orderDto, HttpStatus.CREATED, "ORDER CREATED");
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
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
    public ResponseEntity<GenericResponse> getCustomerWishList(@PathVariable("customerId") int customerId) {

        try {
            CustomerWishListDto customerWishListDto = customerService.getCustomerWishList(customerId);
            GenericResponse<CustomerWishListDto> response =
                    new GenericResponse<>(customerWishListDto, HttpStatus.OK, "REQUEST SUCCESSFUL");
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            e.printStackTrace();
            GenericResponse<ProductWishListDto> response =
                    new GenericResponse<>(null, HttpStatus.INTERNAL_SERVER_ERROR, "COULDN'T ADD PRODUCT");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PostMapping("/{customerId}/wishlist")
    public ResponseEntity<GenericResponse> addWishListProduct(@PathVariable("customerId") int customerId,
                                                              @RequestBody WishlistProdRequest prodRequest) {

        try {
            prodRequest.setCustomerId(customerId);
            ProductWishListDto product = wishListService.addProduct(prodRequest);
            GenericResponse<ProductWishListDto> response =
                    new GenericResponse<>(product, HttpStatus.CREATED, "PRODUCT ADDED");

            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            e.printStackTrace();
            GenericResponse<ProductWishListDto> response =
                    new GenericResponse<>(null, HttpStatus.INTERNAL_SERVER_ERROR, "COULDN'T ADD PRODUCT");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @DeleteMapping("/{customerId}/wishlist")
    public ResponseEntity<GenericResponse> deleteWishListProduct(@PathVariable("customerId") int customerId,
                                                                 @RequestBody WishlistProdRequest prodRequest) {
        try {
            prodRequest.setCustomerId(customerId);
            Boolean isDeleted = wishListService.deleteProduct(prodRequest);
            GenericResponse<ProductWishListDto> response =
                    new GenericResponse(isDeleted, HttpStatus.OK, "PRODUCT REMOVED");

            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            e.printStackTrace();
            GenericResponse<ProductWishListDto> response =
                    new GenericResponse<>(null, HttpStatus.INTERNAL_SERVER_ERROR, "COULDN'T DELETE PRODUCT");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
    //=================================== Cart Api ==================================

    //TODO ====> Ask About UserId
    @GetMapping(path = "/{cusomterId}/carts")
    public ResponseEntity<GenericResponse> getCart(@PathVariable("cusomterId") Integer userId) {
        CartDto cartDto = cartService.getCartByUserId(userId);

        GenericResponse<CartDto> response =
                new GenericResponse(cartDto, HttpStatus.OK, "REQUEST_SUCCESS");

        return ResponseEntity.ok(response);
    }

    @PostMapping(path = "/{cusomterId}/carts")
    public ResponseEntity<GenericResponse> addCartItem(@PathVariable("cusomterId") Integer userId,
                                                       @RequestBody CartItemRequest cartItemRequest) {
        cartItemRequest.setCustomerId(userId);
        CartItemDto cartItemDto = cartService.addCartItem(cartItemRequest);

        GenericResponse<CartItemDto> response =
                new GenericResponse(cartItemDto, HttpStatus.OK, "REQUEST_SUCCESS");

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping(path = "/{cusomterId}/carts")
    public ResponseEntity<GenericResponse> updateCartItem(@PathVariable("cusomterId") Integer userId,
                                                          @RequestBody CartItemRequest cartItemRequest) {
        cartItemRequest.setCustomerId(userId);
        CartItemDto cartItemDto = cartService.updateCartItem(cartItemRequest);

        GenericResponse<CartItemDto> response =
                new GenericResponse(cartItemDto, HttpStatus.OK, "REQUEST_SUCCESS");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping(path = "/{cusomterId}/carts")
    public ResponseEntity<GenericResponse> deleteCartItem(@PathVariable("cusomterId") Integer userId,
                                                          @RequestBody CartItemRequest cartItemRequest) {
        cartItemRequest.setCustomerId(userId);
        boolean isDeleted = cartService.deleteCartItem(cartItemRequest);
        GenericResponse<Boolean> response =
                new GenericResponse(isDeleted, HttpStatus.OK, "REQUEST_SUCCESS");
        return ResponseEntity.ok(response);
    }

    @PreAuthorize("CUSTOMER_ROLE")
    @GetMapping(path = "/{customerId}/checkout")
    public ResponseEntity<GenericResponse<CheckoutStateDto>> validateCheckout(@PathVariable("customerId") Integer customerId) {
        try {
            CartDto cartDto = cartService.getCartByUserId(customerId);
            if (cartDto.getItems().isEmpty()) {
                throw new Exception("CART IS EMPTY");
            }
            CheckoutStateDto response = checkoutService.validateCartItemsQuantity(cartDto);
            return ResponseEntity.status(HttpStatus.OK).body(new GenericResponse<>(response,
                    HttpStatus.OK, "Response received Successfully"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new GenericResponse<>(null,
                    HttpStatus.BAD_REQUEST, e.getMessage()));
        }
    }

    @PreAuthorize("CUSTOMER_ROLE")
    @PostMapping(path = "/{customerId}/payment")
    @Transactional
    public ResponseEntity<GenericResponse<?>> applyCheckout(@PathVariable("customerId") Integer customerId,
                                                            @RequestBody PaymentInfoDto paymentInfo) {
        try {
            CartDto cartDto = cartService.getCartByUserId(customerId);
            if (checkoutService.validatePayment(paymentInfo, cartDto)) {
                OrderDto orderDto = checkoutService.doCheckout(paymentInfo, cartDto);
                System.out.println(orderDto);
                return ResponseEntity.status(HttpStatus.OK).body(new GenericResponse<>(orderDto,
                        HttpStatus.OK, "Checkout Done Successfully"));
            }
            throw new Exception("Payment Failed");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new GenericResponse<>(null,
                    HttpStatus.BAD_REQUEST, e.getMessage()));
        }
    }
//    @PreAuthorize("ROLE_ADMIN")
    @GetMapping(path = "/{customerId}/details")
    public ResponseEntity<GenericResponse<CustomerDetailsDto>> getCustomerDetails(@PathVariable("customerId") Integer customerId){
        try {
            CustomerDetailsDto customerDetailsDto = customerService.getCustomerDetailsById(customerId);
            return ResponseEntity.status(HttpStatus.OK).body(new GenericResponse<>(customerDetailsDto,
                    HttpStatus.OK,"REQUEST SUCCEEDED"));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.OK).body(new GenericResponse<>(null,
                    HttpStatus.BAD_REQUEST,e.getMessage()));
        }
    }
}
