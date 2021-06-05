package com.store.service;

import com.store.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<Product> getAllProducts();

    Optional<Product> getProductById(Integer id);

    Product addProduct(Product product);

    Product updateProduct(Product product);

    void deleteProduct(Integer id);
}
