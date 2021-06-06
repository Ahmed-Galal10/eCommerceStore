package com.store.service;



import com.store.dtos.customer.CustomerDto;
import com.store.dtos.customer.CustomerOrderDto;
import com.store.dtos.customer.CustomerReviewDto;
import com.store.model.Customer;
import com.store.model.User;

import java.util.List;


public interface CustomerService {

    List<CustomerDto> getAllCustomers();

    CustomerDto getCustomerById(Integer customerId);

    CustomerDto addCustomer(CustomerDto customerDto);

    void deleteCustomer(Integer customerId);

    void updateCustomer(CustomerDto customerDto);

    List<CustomerOrderDto> getCustomerOrders(Integer customerId);

    List<CustomerReviewDto> getCustomerReviews(Integer customerId);
}
