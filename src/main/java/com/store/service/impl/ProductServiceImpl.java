package com.store.service.impl;

import com.store.dtos.product.ProdDetailDto;
import com.store.dtos.product.ProductImagesDto;
import com.store.dtos.product.ProductWrapperDto;
import com.store.dtos.review.ReviewDto;
import com.store.model.*;
import com.store.dtos.seller.SellerProductDto;
import com.store.repository.*;
import com.store.service.ProductService;
import com.store.util.mappers.EntityDtoMapper;
import com.store.util.mappers.ReviewMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@SessionScope
public class ProductServiceImpl implements ProductService {

    private ProductRepo productRepo;
    private SubCategoryRepo subCategoryRepo;
    private ReviewRepo reviewRepo;
    private ProductImagesRepo productImagesRepo;
    private SellerRepo sellerRepo;

    private EntityDtoMapper<Product, ProdDetailDto> productMapperAPI;
    private EntityDtoMapper<ProdImages, ProductImagesDto> productImagesMapper;
    private EntityDtoMapper<Product, SellerProductDto> mapper;

    @Autowired
    public ProductServiceImpl(ProductRepo productRepo,
                              SubCategoryRepo subCategoryRepo,
                              ReviewRepo reviewRepo,
                              EntityDtoMapper<Product, ProdDetailDto> productMapperAPI,
                              EntityDtoMapper<ProdImages, ProductImagesDto> productImagesMapper,
                              ProductImagesRepo productImagesRepo,
                              EntityDtoMapper<Product, SellerProductDto> sellerProductMapper,
                              SellerRepo sellerRepo) {
        this.productRepo = productRepo;
        this.subCategoryRepo = subCategoryRepo;
        this.reviewRepo = reviewRepo;
        this.productMapperAPI = productMapperAPI;
        this.productImagesMapper = productImagesMapper;
        this.productImagesRepo = productImagesRepo;
        this.mapper = sellerProductMapper;
        this.sellerRepo = sellerRepo;
    }

    @Override
    public List<ProdDetailDto> getAllProducts() {
        List<Product> products = productRepo.findAll();
        return productMapperAPI.entityListToDtoList(products);
    }

    @Override
    public ProductWrapperDto getAllProductsByFilters(Integer pageNumber,
                                                     Integer pageSize, Double priceMin,
                                                     Double priceMax,
                                                     List<Integer> subCategoriesIds,
                                                     String nameSearch) {

        ProductWrapperDto productWrapperDto = new ProductWrapperDto();

        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("name"));

        // default value for subcategory
        if (subCategoriesIds == null) {
            subCategoriesIds = subCategoryRepo.findAll().stream().map(Subcategory::getId).collect(Collectors.toList());
        }
        List<Subcategory> subcategories = subCategoryRepo.findByIdIn(subCategoriesIds);

        if (nameSearch != null && !nameSearch.equals("%")) {
            nameSearch = "%".concat(nameSearch.concat("%"));
        }

        Page<Product> page = productRepo.findBySubcategoryInAndPriceBetweenAndNameLikeIgnoreCase(
                subcategories, priceMin, priceMax, nameSearch, pageable);

        if (page.hasContent()) {
            List<Product> products = page.getContent();

            List<ProdDetailDto> prodDetailDtos = productMapperAPI.entityListToDtoList(products);

            productWrapperDto.setProducts(prodDetailDtos);
            productWrapperDto.setTotalPages(page.getTotalPages());
            productWrapperDto.setTotalElements(page.getTotalElements());

            return productWrapperDto;
        } else {
            return null;
        }
    }


    @Override
    public ProdDetailDto getProductById(Integer id) {
        Product product = productRepo.findById(id).orElseThrow(() -> new RuntimeException("Not found"));

        ProdDetailDto prodDetailDto = productMapperAPI.toDto(product);

        List<ProdImages> prodImages = productImagesRepo.findProdImagesByProduct(product);

        List<String> imagesUrls = prodImages
                .stream()
                .map(images -> images.getImageUrl()).collect(Collectors.toList());

        prodDetailDto.setProdImages(imagesUrls);

        return prodDetailDto;
    }

    @Override
    public ProdDetailDto addOrUpdateProduct(ProdDetailDto prodDetailDto) {

        Integer subCategoryId = prodDetailDto.getSubcategoryId();
        Integer sellerId = prodDetailDto.getSellerId();
        Product product = new Product();

        Set<ProdImages> prodImagesSet = new HashSet<>();

        System.out.println(sellerRepo.findAll().size());
        System.out.println(sellerId);
        System.out.println(subCategoryId);

        Seller seller = sellerRepo.getOne(sellerId);
        Subcategory subcategory = subCategoryRepo.getOne(subCategoryId);
        product.setName(prodDetailDto.getProductName());
        product.setDescription(prodDetailDto.getProductDescription());
        product.setImg(prodDetailDto.getProductImg());
        product.setPrice(prodDetailDto.getProductPrice());
        product.setQuantity(prodDetailDto.getProductQuantity());

        product.setSubcategory(subcategory);
        product.setUser(seller);

        prodDetailDto.getProdImages().forEach(imgUrl -> {
            ProdImages prodImage = new ProdImages();
            prodImage.setProduct(product);
            prodImage.setImageUrl(imgUrl);
            prodImagesSet.add(prodImage);
        });
        product.setProdImageses(prodImagesSet);

        Product persistedProduct = productRepo.save(product);
        ProdDetailDto dto = productMapperAPI.toDto(persistedProduct);
        return dto;
    }

    @Override
    public void deleteProduct(Integer id) {

        productRepo.deleteById(id);
    }

    @Override
    public List<ReviewDto> getProductReviews(Integer productId) {
        Product product = productRepo.getOne(productId);

        ReviewMapper reviewMapper = new ReviewMapper();

        List<Review> reviews = reviewRepo.findReviewsByProduct(product);

        System.out.println(reviews);

        return reviewMapper.entityListToDtoList(reviews);
    }

    @Override
    public List<SellerProductDto> getProductsByUserId(int sellerId) {

        List<SellerProductDto> dtos = mapper.entityListToDtoList(productRepo.findByUser_Id(sellerId));

        return dtos;
    }

    @Override
    public ProductImagesDto addImageToProduct(ProductImagesDto productImagesDto, Integer productId) {
        ProdImages prodImages = productImagesMapper.toEntity(productImagesDto);

        Product product = productRepo.getOne(productId);
        prodImages.setProduct(product);

        prodImages = productImagesRepo.save(prodImages);

        return productImagesMapper.toDto(prodImages);
    }
}
