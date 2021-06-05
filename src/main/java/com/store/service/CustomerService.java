package com.store.service;

import com.store.dto.CustomerDto;
import com.store.dto.CustomerOrderDto;
import com.store.model.domain.User;

import java.util.List;


public interface CustomerService {

    List<CustomerDto> getAllCustomers();

    CustomerDto getCustomerById(Integer customerId);

    CustomerDto addCustomer(User myUser);

    void deleteCustomer(Integer customerId);

    void updateCustomer(CustomerDto customerDto);

    List<CustomerOrderDto> getCustomerOrders(Integer customerId);
}
