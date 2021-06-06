package com.store.service.impl;


import com.store.dtos.customer.*;
import com.store.model.Customer;
import com.store.model.Order;
import com.store.model.User;
import com.store.repository.CustomerRepo;
import com.store.service.CustomerService;
import com.store.util.mappers.CustomerMapper;
import com.store.util.mappers.CustomerOrderMapper;
import com.store.util.mappers.EntityDtoMapper;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
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
    private ModelMapper modelMapper;

    public CustomerServiceImpl() {
    }

    @Override
    public List<CustomerDto> getAllCustomers() {

        List<Customer> userList = customerRepo.findAll();
        System.out.println(userList);
        System.out.println("before");

        EntityDtoMapper<Customer, CustomerDto> mapper = new CustomerMapper();
        List<CustomerDto> customerDtoList = mapper.entityListToDtoList(userList);

        System.out.println("after");
        System.out.println(customerDtoList);

        return customerDtoList;
    }


    @Override
    public CustomerDto getCustomerById(Integer customerId) {

        Optional<Customer> customer = customerRepo.findById(customerId);

        System.out.println(customer.get());

        //CustomerDto customerDto = modelMapper.map(user, CustomerDto.class);
        EntityDtoMapper<Customer, CustomerDto> mapper = new CustomerMapper();
        CustomerDto customerDto = mapper.toDto(customer.get());

        System.out.println(customerDto);
        return customerDto;
    }

    @Override
    public CustomerDto addCustomer(Customer customer) {

        Customer savedCustomer = customerRepo.save(customer);
        //CustomerDto customerDto = modelMapper.map(user, CustomerDto.class);
        EntityDtoMapper<Customer, CustomerDto> mapper = new CustomerMapper();
        CustomerDto customerDto = mapper.toDto(savedCustomer);

        return customerDto;

    }

    @Override
    public void deleteCustomer(Integer customerId) {
        customerRepo.deleteById(customerId);
    }

    @Override
    public void updateCustomer(CustomerDto customerDto) {

        EntityDtoMapper<Customer, CustomerDto> mapper = new CustomerMapper();
        Customer customer = mapper.toEntity(customerDto);
        customerRepo.save(customer);
    }

    @Override
    public List<CustomerOrderDto> getCustomerOrders(Integer customerId) {

        Optional<Customer> customer = customerRepo.findById(customerId);

        List<Order> orderList = customer.get().getOrders().stream().collect(Collectors.toList());

        EntityDtoMapper<Order, CustomerOrderDto> mapper = new CustomerOrderMapper();
        List<CustomerOrderDto> customerOrderDtoList = mapper.entityListToDtoList(orderList);

        return customerOrderDtoList;

    }


}
