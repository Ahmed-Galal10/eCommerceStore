package com.store.util.mappers;

import com.store.dtos.customer.ProductReviewDto;
import com.store.model.Product;

public class ProductReviewMapper extends EntityDtoMapper<Product, ProductReviewDto>{

    @Override
    public ProductReviewDto toDto(Product entity) {

        ProductReviewDto productReviewDto = new ProductReviewDto();

        productReviewDto.setId(entity.getId());
        productReviewDto.setName(entity.getName());
        productReviewDto.setDescription(entity.getDescription());
        productReviewDto.setPrice(entity.getPrice());
        productReviewDto.setOnSale(entity.isIsOnSale());

        return productReviewDto;
    }

    @Override
    public Product toEntity(ProductReviewDto dto) {

        return null;
    }
}
