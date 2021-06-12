package com.store.service;

import com.store.dtos.product.ProdDetailDto;
import com.store.dtos.product.ProductImagesDto;
import com.store.dtos.review.ReviewDto;
import com.store.dtos.seller.ProductDto;

import java.util.List;

public interface ProductService {
    List<ProdDetailDto> getAllProducts();

    List<ProdDetailDto> getAllProductsByFilters(Integer pageNumber,
                                                Double priceStart,
                                                Double priceEnd,
                                                List<Integer> subCategoriesIds,
                                                String nameSearch);

    ProdDetailDto getProductById(Integer id);

    ProdDetailDto addOrUpdateProduct(ProdDetailDto prodDetailDto);

    List<ReviewDto> getProductReviews(Integer id, Integer pageNumber);

    List<ProductDto> getProductsByUserId(int sellerId);

    void deleteProduct(Integer id);

    ProductImagesDto addImageToProduct(ProductImagesDto productImagesDto, Integer productId);
}
