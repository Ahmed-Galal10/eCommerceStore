package com.store.service.impl;

import com.store.dtos.product.ProdDetailDto;
import com.store.model.Product;
import com.store.model.Subcategory;
import com.store.repo.ProductImagesRepository;
import com.store.repo.ProductRepository;
import com.store.repository.SubCategoryRepo;
import com.store.service.ProductService;
import com.store.util.ProductMapper;
import io.swagger.models.auth.In;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;

import com.store.dtos.seller.ProductDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.annotation.SessionScope;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
@SessionScope
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepo;
    private ProductImagesRepository productImagRepo;
    private ModelMapper modelMapper;
    private SubCategoryRepo subCategoryRepo;

    private ProductMapper mapper;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepo,
                              ProductImagesRepository productImagRepo,
                              ModelMapper modelMapper,
                              SubCategoryRepo subCategoryRepo) {
        this.productRepo = productRepo;
        this.productImagRepo = productImagRepo;
        this.modelMapper = modelMapper;
        this.subCategoryRepo = subCategoryRepo;
    }

    @Override
    public List<ProdDetailDto> getAllProducts() {
        List<Product> products = productRepo.findAll();
        return modelMapper.map(products, new TypeToken<List<ProdDetailDto>>() {
        }.getType());
    }

    @Override
    public List<ProdDetailDto> getAllProductsByFilters(Integer pageNumber,
                                                       Double priceMin,
                                                       Double priceMax,
                                                       List<Integer> subCategoriesIds,
                                                       String nameSearch) {

        Pageable pageable = PageRequest.of(pageNumber, 3, Sort.by("name"));

        // default value for subcategory
        if (subCategoriesIds == null) {
            subCategoriesIds = subCategoryRepo.findAll().stream().map(Subcategory::getId).collect(Collectors.toList());
        }
        List<Subcategory> subcategories = subCategoryRepo.findByIdIn(subCategoriesIds);

        if (nameSearch != null && !nameSearch.equals("%")) {
            nameSearch = "%".concat(nameSearch.concat("%"));
        }

        System.out.println(nameSearch);

        Page<Product> page = productRepo.findBySubcategoryInAndPriceBetweenAndNameLikeIgnoreCase(
                subcategories, priceMin, priceMax, nameSearch, pageable);

        if (page.hasContent()) {
            List<Product> products = page.getContent();

            return modelMapper.map(products, new TypeToken<List<ProdDetailDto>>() {
            }.getType());
        } else {
            return null;
        }
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
    public List<ProductDto> getProductsByUserId(int sellerId) {

//        return mapper.entityListToDtoList(productRepo.findByUser_Id(sellerId));
        return null;
    }


}
