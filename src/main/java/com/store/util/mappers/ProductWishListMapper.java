package com.store.util.mappers;

import com.store.dtos.customer.ProductWishListDto;
import com.store.model.Product;

public class ProductWishListMapper extends EntityDtoMapper<Product, ProductWishListDto>{

    @Override
    public ProductWishListDto toDto(Product entity) {

        ProductWishListDto productReviewDto = new ProductWishListDto();

        productReviewDto.setId(entity.getId());
        productReviewDto.setName(entity.getName());

        productReviewDto.setPrice(entity.getPrice());
        productReviewDto.setOnSale(entity.isIsOnSale());
        productReviewDto.setImg(entity.getImg());

        return productReviewDto;
    }

    @Override
    public Product toEntity(ProductWishListDto dto) {

        return null;
    }
}
