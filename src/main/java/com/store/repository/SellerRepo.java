package com.store.repository;

import com.store.model.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SellerRepo extends JpaRepository<Seller, Integer> {

    boolean existsByEmail(String email);
}
