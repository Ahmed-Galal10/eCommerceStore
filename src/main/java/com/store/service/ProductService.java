package com.store.service;

import com.store.dtos.product.ProdDetailDto;
import com.store.dtos.seller.SellerProductDto;

import java.util.List;

public interface ProductService {
    List<ProdDetailDto> getAllProducts();

    ProdDetailDto getProductById(Integer id);

    ProdDetailDto addOrUpdateProduct(ProdDetailDto prodDetailDto);

    List<SellerProductDto> getProductsByUserId(int sellerId);

    void deleteProduct(Integer id);
}
