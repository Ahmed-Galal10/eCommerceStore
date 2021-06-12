package com.store.repo;

import com.store.model.Product;
import com.store.model.Subcategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    Page<Product> findBySubcategoryInAndPriceBetweenAndNameLikeIgnoreCase(Collection<Subcategory> subcategory, Double price, Double price2, String name, Pageable pageable);

}
