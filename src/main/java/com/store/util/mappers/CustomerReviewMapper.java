package com.store.util.mappers;

import com.store.dtos.customer.CustomerReviewDto;
import com.store.model.Review;

public class CustomerReviewMapper extends EntityDtoMapper<Review, CustomerReviewDto>{

    @Override
    public CustomerReviewDto toDto(Review entity) {

        CustomerReviewDto customerReviewDto = new CustomerReviewDto();

        customerReviewDto.setId(entity.getId());
        customerReviewDto.setRating(entity.getRating());
        customerReviewDto.setReviewText(entity.getReviewText());
        customerReviewDto.setCreatedAt(entity.getCreatedAt());

        return customerReviewDto;
    }

    @Override
    public Review toEntity(CustomerReviewDto dto) {

        return null;
    }
}
