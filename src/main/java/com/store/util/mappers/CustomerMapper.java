package com.store.util.mappers;

import com.store.dtos.category.CategoryDto;
import com.store.dtos.customer.CustomerDto;
import com.store.model.Category;
import com.store.model.Customer;

public class CustomerMapper extends EntityDtoMapper<Customer, CustomerDto>{

    @Override
    public CustomerDto toDto(Customer entity) {

        CustomerDto customerDto = new CustomerDto();

        customerDto.setId(entity.getId());
        customerDto.setName(entity.getName());
        customerDto.setAddress(entity.getAddress());
        customerDto.setBalance(entity.getBalance());
        customerDto.setEmail(entity.getEmail());
        customerDto.setEmailVerified(entity.getIsEmailVerified());
        customerDto.setDeleted(entity.getIsDeleted());
        customerDto.setPhone(entity.getPhone());
        customerDto.setRegDate(entity.getRegDate());
        customerDto.setImage(entity.getImage());
        customerDto.setPassword(entity.getPassword());
        return customerDto;
    }

    @Override
    public Customer toEntity(CustomerDto dto) {

        Customer customer = new Customer();

        customer.setId(dto.getId());
        customer.setName(dto.getName());
        customer.setAddress(dto.getAddress());
        customer.setBalance(dto.getBalance());
        customer.setEmail(dto.getEmail());
        customer.setIsEmailVerified(dto.getEmailVerified());
        customer.setIsDeleted(dto.getDeleted());
        customer.setPhone(dto.getPhone());
        customer.setRegDate(dto.getRegDate());
        customer.setImage(dto.getImage());
        customer.setPassword(dto.getPassword());

        return customer;
    }
}
