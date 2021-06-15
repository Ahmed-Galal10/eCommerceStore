package com.store.util.mappers;

import com.store.dtos.customer.CustomerRequestDto;
import com.store.model.Customer;

public class CustomerRequestMapper extends EntityDtoMapper<Customer, CustomerRequestDto>{

    @Override
    public CustomerRequestDto toDto(Customer entity) {

        CustomerRequestDto customerRequestDto = new CustomerRequestDto();

        customerRequestDto.setId(entity.getId());
        customerRequestDto.setName(entity.getName());
        customerRequestDto.setAddress(entity.getAddress());
        customerRequestDto.setEmail(entity.getEmail());
        customerRequestDto.setPhone(entity.getPhone());
        customerRequestDto.setPassword(entity.getPassword());

        return customerRequestDto;
    }

    @Override
    public Customer toEntity(CustomerRequestDto dto) {

        Customer customer = new Customer();

        customer.setId(dto.getId());
        customer.setName(dto.getName());
        customer.setAddress(dto.getAddress());
        customer.setEmail(dto.getEmail());
        customer.setPhone(dto.getPhone());
        customer.setPassword(dto.getPassword());

        return customer;
    }
}
