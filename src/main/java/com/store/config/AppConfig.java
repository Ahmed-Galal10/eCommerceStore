package com.store.config;

import com.store.service.CustomerService;
import com.store.service.impl.CustomerServiceImpl;
import com.store.util.mappers.*;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }


    // ============================= Mappers ================================
    @Bean
    public CartItemMapper getCartItemMapper(){
        return new CartItemMapper();
    }

    @Bean
    public CategoryMapper getCategoryMapper(){
        return new CategoryMapper();
    }

    @Bean
    public SubCategoryMapper getSubCategoryMapper(){
        return new SubCategoryMapper();
    }

    @Bean
    public CustomerMapper getCustomerMapper(){
        return new CustomerMapper();
    }

    // ============================= Mappers ================================


}
