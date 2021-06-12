package com.store.service;

import com.store.dtos.product.ProdDetailDto;
import com.store.dtos.seller.ProductDto;
import org.springframework.data.domain.Pageable;

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

    List<ProductDto> getProductsByUserId(int sellerId);

    void deleteProduct(Integer id);
}
