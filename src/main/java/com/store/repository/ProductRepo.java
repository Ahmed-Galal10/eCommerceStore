package com.store.repository;

import com.store.model.Product;
import com.store.model.Subcategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface ProductRepo extends JpaRepository<Product, Integer> {

    Page<Product> findBySubcategoryInAndPriceBetweenAndNameLikeIgnoreCase(Collection<Subcategory> subcategory, Double price, Double price2, String name, Pageable pageable);

    Page<Product> findByUser_Id(int sellerId, Pageable pageable);

    Integer countAllByUser_Id(Integer sellerId);

    @Query(value = "SELECT count(*) FROM product p1 " +
            "INNER JOIN product_wishlist p2 " + "ON p1.id = p2.product_id " +
            "WHERE p2.product_id = ?1", nativeQuery = true)
    Integer countProductInWishListsById(Integer productId);


    @Query(value = "SELECT max(price) FROM product", nativeQuery = true)
    Double findMaxPrice();
}
