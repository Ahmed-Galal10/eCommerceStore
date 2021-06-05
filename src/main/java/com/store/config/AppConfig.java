package com.store.config;

import com.store.repo.CustomerRepo;
import com.store.service.CustomerService;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public CustomerService getCustomerService(){
        return new CustomerService();
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }


}
