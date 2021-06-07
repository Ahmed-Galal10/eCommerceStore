package com.store.service;

import com.store.dtos.seller.ProductDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {
    List<ProductDto> getProductsByUserId(int sellerId);
}
