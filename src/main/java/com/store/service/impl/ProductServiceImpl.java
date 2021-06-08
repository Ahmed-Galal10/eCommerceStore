package com.store.service.impl;

import com.store.dtos.product.ProdDetailDto;
import com.store.dtos.seller.SellerProductDto;
import com.store.model.Product;
import com.store.repo.ProductImagesRepository;
import com.store.repo.ProductRepository;
import com.store.service.ProductService;
import com.store.util.ProductMapper;
import com.store.util.mappers.SellerProductMapper;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {


    private final ProductRepository productRepo;
    private final ProductImagesRepository productImagRepo;
    private final ModelMapper modelMapper;

    private final SellerProductMapper mapper;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepo, ProductImagesRepository productImagRepo, ModelMapper modelMapper, SellerProductMapper mapper) {
        this.productRepo = productRepo;
        this.productImagRepo = productImagRepo;
        this.modelMapper = modelMapper;
        this.mapper = mapper;
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

    @Override
    public List<SellerProductDto> getProductsByUserId(int sellerId) {

        List<SellerProductDto> dtos = mapper.entityListToDtoList(productRepo.findByUser_Id(sellerId));

        return dtos;

    }


}
