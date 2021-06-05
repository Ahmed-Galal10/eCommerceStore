package com.store.service;


import com.store.dto.CustomerDto;
import com.store.model.Customers;
import com.store.model.domain.User;
import com.store.repo.CustomerRepo;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    CustomerRepo customerRepo;

    @Autowired
    private ModelMapper modelMapper;

    public CustomerService() {
    }

    public List<CustomerDto> getAllCustomers() {

        List<User> userList = customerRepo.findAll();

        List<CustomerDto> customerDtoList
                = modelMapper.map(userList, new TypeToken<List<CustomerDto>>() {}.getType());
        return customerDtoList;
    }


    public CustomerDto getCustomerById(Integer customerId) {

        Optional<User> user = customerRepo.findById(customerId);
        CustomerDto customerDto = modelMapper.map(user, CustomerDto.class);

        return customerDto;
    }

    public CustomerDto addCustomer(User myUser){

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


}
