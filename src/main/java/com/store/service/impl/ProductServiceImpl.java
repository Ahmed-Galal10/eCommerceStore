package com.store.service.impl;

import com.store.dtos.product.ProdDetailDto;
import com.store.dtos.product.ProductImagesDto;
import com.store.dtos.product.ProductWrapperDto;
import com.store.dtos.product.SellerProdDetailDto;

import com.store.exceptions.ReviewException;
import com.store.exceptions.ShopException;

import com.store.model.ProdImages;
import com.store.model.Product;
import com.store.model.Review;
import com.store.model.Subcategory;


import com.store.dtos.review.ReviewDto;
import com.store.dtos.seller.SellerProductDto;
import com.store.model.*;
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
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@SessionScope
public class ProductServiceImpl implements ProductService {



    private final ProductRepo productRepo;
    private final SubCategoryRepo subCategoryRepo;
    private final ReviewRepo reviewRepo;
    private final ProductImagesRepo productImagesRepo;
    private final SellerRepo sellerRepo;
    private final OrderItemRepo orderItemRepo;
    private final CustomerRepo customerRepo;
    private final OrderRepo orderRepo;

    private final EntityDtoMapper<Product, ProdDetailDto> productMapperAPI;
    private final EntityDtoMapper<ProdImages, ProductImagesDto> productImagesMapper;
    private final EntityDtoMapper<Product, SellerProductDto> mapper;
    private final EntityDtoMapper<Review, ReviewDto> reviewMapper;

    @Autowired
    public ProductServiceImpl(ProductRepo productRepo,
                              SubCategoryRepo subCategoryRepo,
                              ReviewRepo reviewRepo,
                              OrderItemRepo orderItemRepo, EntityDtoMapper<Product, ProdDetailDto> productMapperAPI,
                              EntityDtoMapper<ProdImages, ProductImagesDto> productImagesMapper,
                              ProductImagesRepo productImagesRepo,
                              EntityDtoMapper<Product, SellerProductDto> sellerProductMapper,
                              EntityDtoMapper<Review, ReviewDto> reviewMapper,
                              SellerRepo sellerRepo,
                              CustomerRepo customerRepo,
                              OrderRepo orderRepo) {
        this.productRepo = productRepo;
        this.subCategoryRepo = subCategoryRepo;
        this.reviewRepo = reviewRepo;
        this.orderItemRepo = orderItemRepo;
        this.productMapperAPI = productMapperAPI;
        this.productImagesMapper = productImagesMapper;
        this.productImagesRepo = productImagesRepo;
        this.mapper = sellerProductMapper;
        this.sellerRepo = sellerRepo;
        this.reviewMapper = reviewMapper;
        this.customerRepo = customerRepo;
        this.orderRepo = orderRepo;
    }

    @Override
    public List<ProdDetailDto> getAllProducts() {
        List<Product> products = productRepo.findAll();
        return productMapperAPI.entityListToDtoList(products);
    }

    @Override
    public ProductWrapperDto<ProdDetailDto> getAllProductsByFilters(Integer pageNumber,
                                                                    Integer pageSize, Double priceMin,
                                                                    Double priceMax,
                                                                    List<Integer> subCategoriesIds,
                                                                    String nameSearch) {


        ProductWrapperDto<ProdDetailDto> productWrapperDto = new ProductWrapperDto<>();

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
            Double maxPrice = productRepo.findMaxPrice();

            productWrapperDto.setProducts(prodDetailDtos);
            productWrapperDto.setTotalPages(page.getTotalPages());
            productWrapperDto.setTotalElements(page.getTotalElements());
            productWrapperDto.setMaxPrice(maxPrice);

            return productWrapperDto;
        } else {
            throw new ShopException("No products found");
        }
    }


    @Override
    public ProdDetailDto getProductById(Integer id) {
//        Product product = productRepo.findById(id).orElseThrow(() -> new ShopException("Not found"));
        Optional<Product> o = productRepo.findById(id);

        Product product = o.orElse(null);

        if (product == null) {
            throw new ShopException("No such product");
        }
        ProdDetailDto prodDetailDto = productMapperAPI.toDto(product);

        List<ProdImages> prodImages = productImagesRepo.findProdImagesByProduct(product);

        List<String> imagesUrls = prodImages
                .stream()
                .map(images -> images.getImageUrl()).collect(Collectors.toList());

        prodDetailDto.setProdImages(imagesUrls);

        return prodDetailDto;
    }

    @Override
    public SellerProdDetailDto getSellerProductDetailById(Integer productId) {

        Product product = productRepo.findById(productId).orElseThrow(() -> new RuntimeException("Not found"));

        System.out.println("product id before : " + product.getId());

        ProdDetailDto prodDetailDto = productMapperAPI.toDto(product);

        System.out.println("product id after : " + prodDetailDto.getId());


        List<ProdImages> prodImages = productImagesRepo.findProdImagesByProduct(product);

        List<String> imagesUrls = prodImages
                .stream()
                .map(images -> images.getImageUrl()).collect(Collectors.toList());

        prodDetailDto.setProdImages(imagesUrls);


        SellerProdDetailDto sellerProdDetailDto = new SellerProdDetailDto();
        sellerProdDetailDto.setSellerProduct(prodDetailDto);

        Double averageRating = reviewRepo.findProductAverageRatingById(productId);

        if(averageRating == null) averageRating = 0.0;
        System.out.println("averageRating is" + averageRating);

        Integer productInWishlists = productRepo.countProductInWishListsById(productId);
        System.out.println("productInWishlists is " + productInWishlists);


//        System.out.println("================>>>>>>>" + orderItemRepo.countTimesSold( productId ));
        Integer soldItemCounter = orderItemRepo.countTimesSold(productId);
        System.out.println("soldItemCounter is " + soldItemCounter);

        sellerProdDetailDto.setAverageRating(averageRating);
        sellerProdDetailDto.setWishListCounter(productInWishlists);
        sellerProdDetailDto.setSoldCounter(soldItemCounter);

        System.out.println("id is " + sellerProdDetailDto.getSellerProduct().getId());
        System.out.println(" sold :" + sellerProdDetailDto.getSoldCounter());
        return sellerProdDetailDto;
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
    public ProductWrapperDto<SellerProductDto> getProductsByUserId(int sellerId, Pageable pageable) {

        Page<Product> productsPage = productRepo.findByUser_Id(sellerId, pageable);

        List<Product> products = productsPage.getContent();
        int totalPages = productsPage.getTotalPages();
        long totalElements = productsPage.getTotalElements();


        List<SellerProductDto> dtos = mapper.entityListToDtoList(products);

        ProductWrapperDto<SellerProductDto> wrapperDto = new ProductWrapperDto<>();

        wrapperDto.setProducts(dtos);
        wrapperDto.setTotalElements(totalElements);
        wrapperDto.setTotalPages(totalPages);

        return wrapperDto;
    }

    @Override
    public ProductImagesDto addImageToProduct(ProductImagesDto productImagesDto, Integer productId) {
        ProdImages prodImages = productImagesMapper.toEntity(productImagesDto);

        Product product = productRepo.getOne(productId);
        prodImages.setProduct(product);

        prodImages = productImagesRepo.save(prodImages);

        return productImagesMapper.toDto(prodImages);
    }

    @Override
    public ReviewDto addReview(Integer id, ReviewDto reviewDto) {
        Review review = reviewMapper.toEntity(reviewDto);
        Product product = productRepo.getOne(id);
        review.setProduct(product);
        Customer customer = customerRepo.getOne(reviewDto.getUserId());
        List<Order> ordersByUser = orderRepo.findOrderByUser(customer);
        List<OrderItem> orderItems = orderItemRepo.findOrderItemsByProductAndOrderIn(product, ordersByUser);
        if (orderItems.isEmpty()) {
            throw new ReviewException("You must buy this product to be able to add review");
        }
        review.setUser(customer);
        Review save = reviewRepo.save(review);

        ReviewDto reviewDto1 = reviewMapper.toDto(save);

        return reviewDto1;
    }

    @Override
    public Long getProductsCount() {

        Long count = productRepo.count();

        return  count;
    }
}
