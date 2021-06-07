package com.store.service;

import com.store.dto.product.ProdDetailDto;

import java.util.List;

public interface ProductService {
    List<ProdDetailDto> getAllProducts();

    ProdDetailDto getProductById(Integer id);

    ProdDetailDto addOrUpdateProduct(ProdDetailDto prodDetailDto);

    void deleteProduct(Integer id);
}
