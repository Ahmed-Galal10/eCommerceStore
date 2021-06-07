package com.store.service.impl;

import com.store.dtos.seller.ProductDto;
import com.store.repository.ProductRepo;
import com.store.service.ProductService;
import com.store.util.mappers.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private ProductRepo productRepo;
    private ProductMapper mapper;

    @Autowired
    public void setup(ProductRepo productRepo, ProductMapper mapper) {
        this.productRepo = productRepo;
        this.mapper = mapper;
    }

    @Override
    public List<ProductDto> getProductsByUserId(int sellerId) {
        return mapper.entityListToDtoList(productRepo.findByUser_Id(sellerId));
    }
}
