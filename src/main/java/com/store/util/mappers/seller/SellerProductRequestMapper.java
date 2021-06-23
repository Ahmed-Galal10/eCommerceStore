package com.store.util.mappers.seller;

import com.store.dtos.product.SellerProductRequestDto;
import com.store.model.Product;
import com.store.util.mappers.EntityDtoMapper;
import org.springframework.stereotype.Component;


@Component
public class SellerProductRequestMapper extends EntityDtoMapper<Product, SellerProductRequestDto> {

    @Override
    public SellerProductRequestDto toDto(Product entity) {
        SellerProductRequestDto sellerProductDto = new SellerProductRequestDto();

        sellerProductDto.setId(entity.getId());
        sellerProductDto.setProductName(entity.getName());
        sellerProductDto.setProductPrice(entity.getPrice());
        sellerProductDto.setProductQuantity(entity.getQuantity());
        sellerProductDto.setProductDescription(entity.getDescription());

        return sellerProductDto;
    }

    @Override
    public Product toEntity(SellerProductRequestDto dto) {
        Product product = new Product();

        product.setId(dto.getId());
        product.setName(dto.getProductName());
        product.setPrice(dto.getProductPrice());
        product.setQuantity(dto.getProductQuantity());
        product.setDescription(dto.getProductDescription());

        return product;
    }
}
