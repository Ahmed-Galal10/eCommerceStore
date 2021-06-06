package com.store.service.impl;


import com.store.dtos.customer.*;
import com.store.model.Customer;
import com.store.model.Order;
import com.store.repository.CustomerRepo;
import com.store.service.CustomerService;
import com.store.util.mappers.EntityDtoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    private EntityDtoMapper<Customer, CustomerDto> customerMapper;

    @Autowired
    private EntityDtoMapper<Order, CustomerOrderDto> customerOrderMapper;

    public CustomerServiceImpl() {
    }

    @Override
    public List<CustomerDto> getAllCustomers() {

        List<Customer> userList = customerRepo.findAll();
        System.out.println(userList);
        System.out.println("before");

        List<CustomerDto> customerDtoList = customerMapper.entityListToDtoList(userList);

        System.out.println("after");
        System.out.println(customerDtoList);

        return customerDtoList;
    }

    @Override
    public CustomerDto getCustomerById(Integer customerId) {

        Optional<Customer> customer = customerRepo.findById(customerId);
        System.out.println(customer.get());

        CustomerDto customerDto = customerMapper.toDto(customer.get());
        System.out.println(customerDto);

        return customerDto;
    }

    @Override
    public CustomerDto addCustomer(CustomerDto customerDto) {

        Customer customer = customerMapper.toEntity(customerDto);
        Customer savedCustomer = customerRepo.save(customer);

        return customerMapper.toDto(savedCustomer);
    }

    @Override
    public void deleteCustomer(Integer customerId) {
        customerRepo.deleteById(customerId);
    }

    @Override
    public void updateCustomer(CustomerDto customerDto) {

        Customer customer = customerMapper.toEntity(customerDto);
        customerRepo.save(customer);
    }

    @Override
    public List<CustomerOrderDto> getCustomerOrders(Integer customerId) {

        Optional<Customer> customer = customerRepo.findById(customerId);

        List<Order> orderList = customer.get().getOrders().stream().collect(Collectors.toList());

        List<CustomerOrderDto> customerOrderDtoList = customerOrderMapper.entityListToDtoList(orderList);

        return customerOrderDtoList;
    }

}
