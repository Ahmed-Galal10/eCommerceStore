package com.store.service;

import com.store.dtos.product.ProdDetailDto;
import com.store.dtos.product.ProductImagesDto;
import com.store.dtos.product.ProductWrapperDto;
import com.store.dtos.product.SellerProdDetailDto;
import com.store.dtos.review.ReviewDto;
import com.store.dtos.seller.SellerProductDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {
    List<ProdDetailDto> getAllProducts();

    ProductWrapperDto<ProdDetailDto> getAllProductsByFilters(Integer pageNumber,
                                                             Integer pageSize, Double priceStart,
                                                             Double priceEnd,
                                                             List<Integer> subCategoriesIds,
                                                             String nameSearch);

    ProdDetailDto getProductById(Integer id);

    ProdDetailDto addOrUpdateProduct(ProdDetailDto prodDetailDto);

    List<ReviewDto> getProductReviews(Integer id);

    ProductWrapperDto<SellerProductDto> getProductsByUserId(int sellerId, Pageable pageable);

    void deleteProduct(Integer id);

    ProductImagesDto addImageToProduct(ProductImagesDto productImagesDto, Integer productId);

    SellerProdDetailDto getSellerProductDetailById(Integer productId);

    ReviewDto addReview(Integer id, ReviewDto reviewDto);

    Long getProductsCount();
}
