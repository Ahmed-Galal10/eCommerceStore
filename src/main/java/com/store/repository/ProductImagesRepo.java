package com.store.repository;

import com.store.model.ProdImages;
import com.store.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductImagesRepo extends JpaRepository<ProdImages, Integer> {
    List<ProdImages> findProdImagesByProduct(Product product);
}
