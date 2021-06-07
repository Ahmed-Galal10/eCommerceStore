package com.store.util;

import com.store.dto.product.ProdDetailDto;
import com.store.model.*;

public class ProductMapper {

    public static Product productDtoToProductEntity(ProdDetailDto prodDetailDto) {
        Product product = new Product();

        product.setId(prodDetailDto.getId());
        product.setName(prodDetailDto.getProductName());
        User user = new User();
        user.setId(prodDetailDto.getUserId());
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

    public static ProdDetailDto ProductEntityToProductDto(Product product) {
        ProdDetailDto prodDetailDto = new ProdDetailDto();

        prodDetailDto.setUserId(product.getId());
        prodDetailDto.setProductName(product.getName());
        prodDetailDto.setUserId(product.getUser().getId());
        prodDetailDto.setProductPrice(product.getPrice());
        prodDetailDto.setProductImg(product.getImg());
        prodDetailDto.setProductQuantity(product.getQuantity());
        prodDetailDto.setOnSale(product.isIsOnSale());
        prodDetailDto.setSubcategoryId(product.getSubcategory().getId());

        return prodDetailDto;
    }
}
