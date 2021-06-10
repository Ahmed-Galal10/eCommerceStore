package com.store.repository;

import com.store.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ReviewRepo extends JpaRepository<Review, Integer> {
}
