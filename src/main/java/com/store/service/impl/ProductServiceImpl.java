package com.store.service.impl;

import com.store.dto.product.ProdDetailDto;
import com.store.model.Product;
import com.store.repo.ProductImagesRepository;
import com.store.repo.ProductRepository;
import com.store.service.ProductService;
import com.store.util.ProductMapper;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepo;
    private ProductImagesRepository productImagRepo;
    private ModelMapper modelMapper;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepo, ProductImagesRepository productImagRepo, ModelMapper modelMapper) {
        this.productRepo = productRepo;
        this.productImagRepo = productImagRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<ProdDetailDto> getAllProducts() {
        List<Product> products = productRepo.findAll();
        return modelMapper.map(products, new TypeToken<List<ProdDetailDto>>() {
        }.getType());
    }

    @Override
    public ProdDetailDto getProductById(Integer id) {
        Product product = productRepo.findById(id).orElseThrow(() -> new RuntimeException("Not found"));
        return ProductMapper.ProductEntityToProductDto(product);
    }

    @Override
    public ProdDetailDto addOrUpdateProduct(ProdDetailDto prodDetailDto) {
        Product product = ProductMapper.productDtoToProductEntity(prodDetailDto);
        product = productRepo.save(product);
        return modelMapper.map(product, ProdDetailDto.class);
    }

    @Override
    public void deleteProduct(Integer id) {
        productRepo.deleteById(id);
    }
}
