package com.store.util.mappers;

import com.store.dtos.product.ProductImagesDto;
import com.store.model.ProdImages;
import com.store.model.Product;

public class ProductImagesMapper extends EntityDtoMapper<ProdImages, ProductImagesDto> {
    @Override
    public ProductImagesDto toDto(ProdImages prodImages) {
        ProductImagesDto productImagesDto = new ProductImagesDto();

        productImagesDto.setImageId(prodImages.getImageId());
        productImagesDto.setProductId(prodImages.getProduct().getId());
        productImagesDto.setImageUrl(prodImages.getImageUrl());

        return productImagesDto;
    }

    @Override
    public ProdImages toEntity(ProductImagesDto productImagesDto) {
        ProdImages prodImages = new ProdImages();

        prodImages.setImageId(prodImages.getImageId());
        Product product = new Product();
        product.setId(productImagesDto.getProductId());
        prodImages.setImageUrl(productImagesDto.getImageUrl());

        return prodImages;
    }
}
