package com.store.controller;


import com.store.dto.CartItemDto;
import com.store.dto.CustomerDto;
import com.store.dto.CustomerOrderDto;
import com.store.model.domain.User;
import com.store.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @GetMapping("/")
    public ResponseEntity<List<CustomerDto>> getAllCustomers() {

        try {
            System.out.println("plaaaaaa");
            return new ResponseEntity<>(customerService.getAllCustomers(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("not plaaaaaa");
            return new ResponseEntity<List<CustomerDto>>(HttpStatus.BAD_REQUEST);
        }

    }
    @GetMapping("/hi")
    public String test(){
        return "eshta ".repeat(5);
    }
    @PostMapping("/")
    public ResponseEntity<String> addCustomer(@RequestBody User user) {
        try {
            customerService.addCustomer(user);
        } catch (Exception e) {
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
            return new ResponseEntity<CustomerDto>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{customerId}")
    public ResponseEntity<String> deleteCustomer(@PathVariable("customerId") int customerId) {
        try {
            customerService.deleteCustomer(customerId);
            return new ResponseEntity<String>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
        }
    }


    @PutMapping("/{customerId}")
    public ResponseEntity<String> updateCustomer(@PathVariable("customerId") int customerId) {
        try {
            CustomerDto customerDto = customerService.getCustomerById(customerId);
            customerService.updateCustomer(customerDto);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/{customerId}/orders")
    public ResponseEntity<List<CustomerOrderDto>> getCustomerOrders(@PathVariable("customerId") int customerId) {
        try {
            List<CustomerOrderDto> customerOrderDtoList = customerService.getCustomerOrders(customerId);
            return new ResponseEntity<List<CustomerOrderDto>>(customerOrderDtoList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{customerId}/cartItems")
    public ResponseEntity<List<CartItemDto>> getCustomerCartItems(@PathVariable("customerId") int customerId) {
        try {
            List<CartItemDto> cartItemDtoList = null;
            return new ResponseEntity<>(cartItemDtoList,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }


}
