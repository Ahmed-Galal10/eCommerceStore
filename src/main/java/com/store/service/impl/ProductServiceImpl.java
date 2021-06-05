package com.store.service.impl;

import com.store.dto.ProdDetailDto;
import com.store.model.Product;
import com.store.repo.ProductImagesRepository;
import com.store.repo.ProductRepository;
import com.store.service.ProductService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    public List<Product> getAllProducts() {
        List<Product> products = productRepo.findAll();

        return modelMapper.map(products, new TypeToken<List<ProdDetailDto>>() {
        }.getType());
    }

    @Override
    public Optional<Product> getProductById(Integer id) {
        return productRepo.findById(id);
    }

    @Override
    public Product addProduct(Product product) {
        return productRepo.save(product);
    }

    @Override
    public Product updateProduct(Product product) {
        return productRepo.save(product);
    }

    @Override
    public void deleteProduct(Integer id) {
        productRepo.deleteById(id);
    }
}
