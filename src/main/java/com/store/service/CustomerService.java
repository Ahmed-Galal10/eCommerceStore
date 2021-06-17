package com.store.service;



import com.store.dtos.customer.*;
import com.store.model.Customer;
import com.store.model.User;

import java.util.List;


public interface CustomerService {

    List<CustomerDto> getAllCustomers();

    CustomerDto getCustomerById(Integer customerId);

    CustomerRequestDto addCustomer(CustomerRequestDto customerDto);

    void deleteCustomer(Integer customerId);

    void updateCustomer(CustomerDto customerDto);

    List<CustomerOrderDto> getCustomerOrders(Integer customerId);

    List<CustomerReviewDto> getCustomerReviews(Integer customerId);

    CustomerWishListDto getCustomerWishList(int customerId);
}
