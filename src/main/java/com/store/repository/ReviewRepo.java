package com.store.repository;

import com.store.model.Product;
import com.store.model.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepo extends JpaRepository<Review, Integer> {

    Page<Review> findReviewsByProduct(Product product, Pageable pageable);

    @Query(value = "SELECT Avg(rating) FROM Review WHERE product_id = ?1", nativeQuery = true)
    Double findProductAverageRatingById(Integer productId);

    List<Review> findReviewsByProduct(Product product);
}
