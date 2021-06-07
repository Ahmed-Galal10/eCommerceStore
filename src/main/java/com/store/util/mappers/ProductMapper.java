package com.store.util.mappers;

import com.store.dtos.seller.ProductDto;
import com.store.model.Product;
import org.springframework.stereotype.Service;

@Service
public class ProductMapper extends EntityDtoMapper<Product, ProductDto> {
    @Override
    public ProductDto toDto(Product entity) {
        ProductDto dto = new ProductDto();
        dto.setName(entity.getName());
        dto.setDescription(entity.getDescription());
        dto.setId(entity.getId());
        dto.setImg(entity.getImg());
        dto.setPrice(entity.getPrice());
        dto.setOnSale(entity.isIsOnSale());
        dto.setQuantity(entity.getQuantity());
        dto.setSubcategoryId(entity.getSubcategory().getId());


        return dto;
    }

    @Override
    public Product toEntity(ProductDto dto) {
        return null;
    }
}
