package com.store.controller;


import com.store.dto.CustomerDto;
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
    public List<CustomerDto> getAllCustomers(){

        return customerService.getAllCustomers();
    }

//    @GetMapping("/")
//    public ResponseEntity<List<CustomerDto>> geAllCustomers() {
//        List<CustomerDto> customerDtoList = customerService.getAllCustomers();
//        return new ResponseEntity<>(customerDtoList, HttpStatus.OK);
//    }

    @PostMapping("/")
    public ResponseEntity<String> addCustomer(@RequestBody User user){

        try {
            customerService.addCustomer(user);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{customerId}")
    public CustomerDto getCustomerById(@PathVariable Integer customerId){

        return customerService.getCustomerById(customerId);
    }

    @DeleteMapping("/{customerId}")
    public ResponseEntity<String> deleteCustomer(@PathVariable int customerId) {
        customerService.deleteCustomer(customerId);

        return new ResponseEntity<>(HttpStatus.OK);
    }


    @PutMapping("/{customerId}")
    public ResponseEntity<String> updateCustomer(@PathVariable Integer customerId) {
        CustomerDto customerDto = customerService.getCustomerById(customerId);
        customerService.updateCustomer(customerDto);

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
