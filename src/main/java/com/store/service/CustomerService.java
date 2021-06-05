package com.store.service;


import com.store.dto.CustomerDto;
import com.store.dto.CustomerOrderDto;
import com.store.model.Customers;
import com.store.model.domain.Order;
import com.store.model.domain.User;
import com.store.repo.CustomerRepo;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    private ModelMapper modelMapper;

    public CustomerService() {
    }

    public List<CustomerDto> getAllCustomers() {

        List<User> userList = customerRepo.findAll();
        System.out.println(userList);
        System.out.println("before");
        List<CustomerDto> customerDtoList
                = modelMapper.map(userList, new TypeToken<List<CustomerDto>>() {
        }.getType());
        System.out.println("after");
        System.out.println(customerDtoList);
        return customerDtoList;
    }


    public CustomerDto getCustomerById(Integer customerId) {

        Optional<User> user = customerRepo.findById(customerId);
        System.out.println(user.get());
        CustomerDto customerDto = modelMapper.map(user, CustomerDto.class);
        System.out.println(customerDto);
        return customerDto;
    }

    public CustomerDto addCustomer(User myUser) {

        User user = customerRepo.save(myUser);
        CustomerDto customerDto = modelMapper.map(user, CustomerDto.class);

        return customerDto;

    }

    public void deleteCustomer(Integer customerId) {
        customerRepo.deleteById(customerId);
    }


    public void updateCustomer(CustomerDto customerDto) {

        User user = modelMapper.map(customerDto, User.class);

        customerRepo.save(user);
    }

    public List<CustomerOrderDto> getCustomerOrders(Integer customerId) {

        Optional<User> customer = customerRepo.findById(customerId);

        List<Order> orderList = customer.get().getOrders().stream().collect(Collectors.toList());

        List<CustomerOrderDto> customerOrderDtoList
                = modelMapper.map(orderList, new TypeToken<List<CustomerOrderDto>>() {
        }.getType());

        return customerOrderDtoList;

    }


}
