package com.store.service;

import com.store.dtos.product.ProdDetailDto;
import com.store.dtos.product.ProductImagesDto;
import com.store.dtos.product.ProductWrapperDto;
import com.store.dtos.review.ReviewDto;
import com.store.dtos.seller.SellerProductDto;

import java.util.List;

public interface ProductService {
    List<ProdDetailDto> getAllProducts();

    ProductWrapperDto getAllProductsByFilters(Integer pageNumber,
                                              Integer pageSize, Double priceStart,
                                              Double priceEnd,
                                              List<Integer> subCategoriesIds,
                                              String nameSearch);

    ProdDetailDto getProductById(Integer id);

    ProdDetailDto addOrUpdateProduct(ProdDetailDto prodDetailDto);

    List<ReviewDto> getProductReviews(Integer id);

    List<SellerProductDto> getProductsByUserId(int sellerId);

    void deleteProduct(Integer id);

    ProductImagesDto addImageToProduct(ProductImagesDto productImagesDto, Integer productId);

    ReviewDto addReview(Integer id, ReviewDto reviewDto);
}
