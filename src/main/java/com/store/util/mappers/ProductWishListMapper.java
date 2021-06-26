package com.store.util.mappers;

import com.store.dtos.customer.ProductWishListDto;
import com.store.model.Product;
import com.store.repository.ReviewRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductWishListMapper extends EntityDtoMapper<Product, ProductWishListDto>{

    @Autowired
    private ReviewRepo reviewRepo;

    @Override
    public ProductWishListDto toDto(Product entity) {

        ProductWishListDto productReviewDto = new ProductWishListDto();

        productReviewDto.setId(entity.getId());
        productReviewDto.setName(entity.getName());

        productReviewDto.setPrice(entity.getPrice());
        productReviewDto.setOnSale(entity.isIsOnSale());
        productReviewDto.setImg(entity.getImg());
        System.out.println(entity.getId()+"asasasas");
        System.out.println(reviewRepo);
        Double avgRating = reviewRepo.findProductAverageRatingById(entity.getId());
        if(avgRating == null) avgRating = 0.0;
        System.out.println(avgRating);
        productReviewDto.setRating( avgRating );

        return productReviewDto;
    }

    @Override
    public Product toEntity(ProductWishListDto dto) {

        return null;
    }
}
