package com.store.service.impl;

import com.store.dtos.product.ProdDetailDto;
import com.store.dtos.review.ReviewDto;
import com.store.model.Product;
import com.store.model.Review;
import com.store.model.Subcategory;
import com.store.repo.ProductImagesRepository;
import com.store.repo.ProductRepository;
import com.store.repository.ReviewRepo;
import com.store.repository.SubCategoryRepo;
import com.store.service.ProductService;
import com.store.util.mappers.EntityDtoMapper;
import com.store.util.mappers.ReviewMapper;

import com.store.dtos.seller.ProductDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.util.List;
import java.util.stream.Collectors;

@Service
@SessionScope
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepo;
    private ProductImagesRepository productImagRepo;
    private SubCategoryRepo subCategoryRepo;
    private ReviewRepo reviewRepo;

    private EntityDtoMapper<Product, ProdDetailDto> productMapperAPI;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepo,
                              ProductImagesRepository productImagRepo,
                              SubCategoryRepo subCategoryRepo,
                              ReviewRepo reviewRepo,
                              EntityDtoMapper<Product, ProdDetailDto> productMapperAPI) {
        this.productRepo = productRepo;
        this.productImagRepo = productImagRepo;
        this.subCategoryRepo = subCategoryRepo;
        this.reviewRepo = reviewRepo;
        this.productMapperAPI = productMapperAPI;
    }

    @Override
    public List<ProdDetailDto> getAllProducts() {
        List<Product> products = productRepo.findAll();
        return productMapperAPI.entityListToDtoList(products);
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

            return productMapperAPI.entityListToDtoList(products);
        } else {
            return null;
        }
    }

    @Override
    public ProdDetailDto getProductById(Integer id) {
        Product product = productRepo.findById(id).orElseThrow(() -> new RuntimeException("Not found"));
        return productMapperAPI.toDto(product);
    }

    @Override
    public ProdDetailDto addOrUpdateProduct(ProdDetailDto prodDetailDto) {
        Product product = productMapperAPI.toEntity(prodDetailDto);
        product = productRepo.save(product);

        return productMapperAPI.toDto(product);
    }

    @Override
    public void deleteProduct(Integer id) {
        productRepo.deleteById(id);
    }

    @Override
    public List<ReviewDto> getProductReviews(Integer productId, Integer pageNumber) {
        Product product = productRepo.getOne(productId);

        ReviewMapper reviewMapper = new ReviewMapper();

        Pageable pageable = PageRequest.of(pageNumber, 3, Sort.by("createdAt"));

        Page<Review> page = reviewRepo.findReviewsByProduct(product, pageable);

        if (page.hasContent()) {
            List<Review> reviews = page.getContent();

            System.out.println(reviews);

            return reviewMapper.entityListToDtoList(reviews);
        } else {
            return null;
        }
    }

    @Override
    public List<ProductDto> getProductsByUserId(int sellerId) {

//        return mapper.entityListToDtoList(productRepo.findByUser_Id(sellerId));
        return null;
    }
}
