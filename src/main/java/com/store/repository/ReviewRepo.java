package com.store.repository;

import com.store.model.Product;
import com.store.model.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepo extends JpaRepository<Review, Integer> {
    Page<Review> findReviewsByProduct(Product product, Pageable pageable);
    List<Review> findReviewsByProduct(Product product);
}
