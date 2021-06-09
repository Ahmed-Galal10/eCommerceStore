package com.store.util.mappers;

import com.store.dtos.customer.CustomerReviewDto;
import com.store.dtos.customer.ProductReviewDto;
import com.store.model.Product;
import com.store.model.Review;
import org.springframework.beans.factory.annotation.Autowired;

public class CustomerReviewMapper extends EntityDtoMapper<Review, CustomerReviewDto>{

    @Autowired
    private EntityDtoMapper<Product, ProductReviewDto> prodReviewMapper ;

    @Override
    public CustomerReviewDto toDto(Review entity) {

        CustomerReviewDto customerReviewDto = new CustomerReviewDto();

        customerReviewDto.setId(entity.getId());
        customerReviewDto.setRating(entity.getRating());
        customerReviewDto.setReviewText(entity.getReviewText());
        customerReviewDto.setCreatedAt(entity.getCreatedAt());

        customerReviewDto.setProduct(prodReviewMapper.toDto(entity.getProduct()));

        return customerReviewDto;
    }

    @Override
    public Review toEntity(CustomerReviewDto dto) {

        return null;
    }
}
