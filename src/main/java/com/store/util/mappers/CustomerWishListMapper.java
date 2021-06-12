package com.store.util.mappers;

import com.store.dtos.customer.CustomerWishListDto;
import com.store.dtos.customer.ProductWishListDto;
import com.store.model.Product;
import com.store.model.Wishlist;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.stream.Collectors;

public class CustomerWishListMapper extends EntityDtoMapper<Wishlist, CustomerWishListDto> {

    @Autowired
    private EntityDtoMapper<Product, ProductWishListDto> prodWishListMapper;

    @Override
    public CustomerWishListDto toDto(Wishlist entity) {

        CustomerWishListDto wishListDto = new CustomerWishListDto();

        wishListDto.setId(entity.getId());
        wishListDto.setProducts(new HashSet<>(prodWishListMapper.entityListToDtoList(entity.getProducts()
                .stream().collect(Collectors.toList()) )));

        return wishListDto;
    }

    @Override
    public Wishlist toEntity(CustomerWishListDto dto) {

        return null;
    }
}
