package com.store.repository;

import com.store.model.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface WishlistRepo  extends JpaRepository<Wishlist, Integer> {


    @Query("FROM Wishlist w  where w.customer.id = ?1")
    Wishlist getWishlistByCustomerId(Integer Id);


}
