package com.store.repository;

import com.store.model.OrderItem;
import com.store.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepo extends JpaRepository<OrderItem, Integer> {

    Integer countOrderItemByProduct(Product product);

}
