package com.store.util.mappers;

import com.store.dtos.review.ReviewDto;
import com.store.model.Review;

import java.util.Date;

public class ReviewMapper extends EntityDtoMapper<Review, ReviewDto>{
    @Override
    public ReviewDto toDto(Review review) {
        ReviewDto reviewDto = new ReviewDto();

        reviewDto.setId(review.getId());
        reviewDto.setUserId(review.getUser().getId());
        reviewDto.setRating(review.getRating());
        reviewDto.setProductId(review.getProduct().getId());
        reviewDto.setCreatedDate(review.getCreatedAt());
        reviewDto.setReviewText(review.getReviewText());

        return reviewDto;
    }

    @Override
    public Review toEntity(ReviewDto dto) {
        return null;
    }
}
