package com.store.util.mappers;

import com.store.dtos.product.ProdDetailDto;
import com.store.model.*;

public class ProductDetailMapper extends EntityDtoMapper<Product, ProdDetailDto> {

    @Override
    public ProdDetailDto toDto(Product product) {
        ProdDetailDto prodDetailDto = new ProdDetailDto();

        prodDetailDto.setId(product.getId());
        prodDetailDto.setProductName(product.getName());
        prodDetailDto.setSellerId(product.getUser().getId());

        prodDetailDto.setSellerName(product.getUser().getName());
        prodDetailDto.setProductPrice(product.getPrice());
        prodDetailDto.setProductImg(product.getImg());
        prodDetailDto.setProductQuantity(product.getQuantity());
        prodDetailDto.setOnSale(product.isIsOnSale());
        prodDetailDto.setSubcategoryId(product.getSubcategory().getId());
        prodDetailDto.setSubCategoryName(product.getSubcategory().getName());
        prodDetailDto.setProductDescription(product.getDescription());
        prodDetailDto.setSubCategoryName(product.getSubcategory().getName());
        prodDetailDto.setId(product.getId());

        return prodDetailDto;
    }

    @Override
    public Product toEntity(ProdDetailDto prodDetailDto) {
        Product product = new Product();

        product.setId(prodDetailDto.getId());
        product.setName(prodDetailDto.getProductName());
        User user = new User();
        user.setId(prodDetailDto.getSellerId());
        product.setUser(user);
        product.setQuantity(prodDetailDto.getProductQuantity());
        product.setImg(prodDetailDto.getProductImg());
        product.setPrice(prodDetailDto.getProductPrice());
        product.setDescription(prodDetailDto.getProductDescription());
        product.setIsOnSale(prodDetailDto.isOnSale());
        Subcategory subcategory = new Subcategory();
        subcategory.setId(prodDetailDto.getSubcategoryId());
        product.setSubcategory(subcategory);

        return product;
    }
}
