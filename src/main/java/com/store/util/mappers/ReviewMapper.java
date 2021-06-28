package com.store.util.mappers;

import com.store.dtos.review.ReviewDto;
import com.store.model.Product;
import com.store.model.Review;
import com.store.model.User;

import java.util.Date;

public class ReviewMapper extends EntityDtoMapper<Review, ReviewDto>{
    @Override
    public ReviewDto toDto(Review review) {
        ReviewDto reviewDto = new ReviewDto();

        reviewDto.setId(review.getId());
        reviewDto.setUserId(review.getUser().getId());
        reviewDto.setUserName(review.getUser().getName());
        reviewDto.setUserImage(review.getUser().getImage());
        reviewDto.setRating(review.getRating());
        reviewDto.setProductId(review.getProduct().getId());
        reviewDto.setCreatedDate(review.getCreatedAt());
        reviewDto.setReviewText(review.getReviewText());

        return reviewDto;
    }

    @Override
    public Review toEntity(ReviewDto reviewDto) {
        Review review = new Review();

        review.setId(reviewDto.getId());
        review.setReviewText(reviewDto.getReviewText());
        review.setRating(reviewDto.getRating());
        review.setCreatedAt(reviewDto.getCreatedDate());

        return review;
    }
}
