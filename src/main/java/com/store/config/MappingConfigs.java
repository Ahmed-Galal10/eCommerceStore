package com.store.config;

import com.store.dtos.customer.*;
import com.store.model.*;
import com.store.dtos.customer.CustomerDto;
import com.store.dtos.customer.CustomerOrderDto;
import com.store.dtos.customer.CustomerReviewDto;
import com.store.dtos.product.ProdDetailDto;
import com.store.dtos.product.ProductImagesDto;
import com.store.util.mappers.ProductDetailMapper;
import com.store.util.mappers.*;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MappingConfigs {

    @Bean
    public ModelMapper modelMapper(){
        return  new ModelMapper();
    }

    // ============================= Custom Mappers ================================
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
    public EntityDtoMapper<Customer, CustomerDto> getCustomerMapper(){
        return new CustomerMapper();
    }

    @Bean
    public EntityDtoMapper<Customer, CustomerRequestDto> getCustomerRequestMapper(){
        return new CustomerRequestMapper();
    }

    @Bean
    public EntityDtoMapper<Order, CustomerOrderDto> getCustomerOrderMapper(){
        return new CustomerOrderMapper();
    }

    @Bean
    public EntityDtoMapper<Review, CustomerReviewDto> getCustomerReviewMapper(){
        return new CustomerReviewMapper();
    }

    @Bean

    public EntityDtoMapper<Wishlist, CustomerWishListDto> getCustomerWishlistMapper(){
        return new CustomerWishListMapper();
    }
    @Bean
    public EntityDtoMapper<Product, ProductWishListDto> getProductWishlistMapper(){
        return new ProductWishListMapper();
    }

    @Bean
    public EntityDtoMapper<Product, ProdDetailDto> getProductMapper() {
        return new ProductDetailMapper();
    }

    @Bean
    public EntityDtoMapper<ProdImages, ProductImagesDto> getProductImagesMapper() {
        return new ProductImagesMapper();
    }

}
