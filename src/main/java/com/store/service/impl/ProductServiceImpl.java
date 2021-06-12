package com.store.service.impl;

import com.store.dtos.product.ProdDetailDto;
import com.store.model.Product;
import com.store.repo.ProductImagesRepository;
import com.store.repo.ProductRepository;
import com.store.service.ProductService;
import com.store.util.mappers.ProductDetailsMapper;

import com.store.dtos.seller.ProductDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {


    private ProductRepository productRepo;
    private ProductImagesRepository productImagRepo;
    private ProductDetailsMapper productMapper;


    @Autowired
    public ProductServiceImpl(ProductRepository productRepo, ProductImagesRepository productImagRepo, ProductDetailsMapper mapper) {
        this.productRepo = productRepo;
        this.productImagRepo = productImagRepo;
        this.productMapper = mapper;
    }

    @Override
    public List<ProdDetailDto> getAllProducts() {
        List<Product> products = productRepo.findAll();
        return productMapper.entityListToDtoList(products);
    }

    @Override
    public ProdDetailDto getProductById(Integer id) {
        Product product = productRepo.findById(id).orElseThrow(() -> new RuntimeException("Not found"));
        return productMapper.toDto(product);
    }

    @Override
    public ProdDetailDto addProduct(ProdDetailDto prodDetailDto) {
        Product product = productMapper.toEntity(prodDetailDto);
        product = productRepo.save(product);
        return productMapper.toDto(product);
    }

    @Override
    public ProdDetailDto updateProduct(Integer id,ProdDetailDto prodDetailDto) {
        prodDetailDto.setId(id);
        Product product = productMapper.toEntity(prodDetailDto);
        product = productRepo.save(product);
        return productMapper.toDto(product);
    }

    @Override
    public void deleteProduct(Integer id) {
        productRepo.deleteById(id);

    }

    @Override
    public List<ProductDto> getProductsByUserId(int sellerId) {

//        return mapper.entityListToDtoList(productRepo.findByUser_Id(sellerId));
        return  null;
    }


}
