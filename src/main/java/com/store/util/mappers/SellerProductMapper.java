package com.store.util.mappers;

import com.store.dtos.seller.SellerProductDto;
import com.store.model.Product;
import org.springframework.stereotype.Service;

@Service("productMapper")
public class SellerProductMapper extends EntityDtoMapper<Product, SellerProductDto> {
    @Override
    public SellerProductDto toDto(Product entity) {

        SellerProductDto dto = new SellerProductDto();

        dto.setName(entity.getName());
        dto.setDescription(entity.getDescription());
        dto.setId(entity.getId());
        dto.setImg(entity.getImg());
        dto.setPrice(entity.getPrice());
        dto.setOnSale(entity.isIsOnSale());
        dto.setQuantity(entity.getQuantity());
        dto.setSubcategoryId(entity.getSubcategory().getId());
        dto.setSubcategoryName(entity.getSubcategory().getName());

        return dto;
    }

    @Override
    public Product toEntity(SellerProductDto dto) {
        return null;
    }
}
