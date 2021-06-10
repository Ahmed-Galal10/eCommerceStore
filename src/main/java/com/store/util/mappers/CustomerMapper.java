package com.store.util.mappers;

import com.store.dtos.category.CategoryDto;
import com.store.dtos.customer.CustomerDto;
import com.store.dtos.customer.CustomerReviewDto;
import com.store.dtos.customer.ProductReviewDto;
import com.store.model.Category;
import com.store.model.Customer;
import com.store.model.Product;
import com.store.model.Review;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.stream.Collectors;

public class CustomerMapper extends EntityDtoMapper<Customer, CustomerDto>{

    @Autowired
    private EntityDtoMapper<Review, CustomerReviewDto> customerReviewMapper ;


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
        customerDto.setReviews(customerReviewMapper.entityListToDtoList(entity.getReviews()
                .stream().collect(Collectors.toList())).stream().collect(Collectors.toSet()));

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
        customer.setReviews(customerReviewMapper.dtoListToEntityList(dto.getReviews()
                .stream().collect(Collectors.toList())).stream().collect(Collectors.toSet()));

        return customer;
    }
}
