package com.store.repository;

import com.store.model.Order;
import com.store.model.OrderItem;
import com.store.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface OrderItemRepo extends JpaRepository<OrderItem, Integer> {

    Integer countOrderItemByProduct(Product product);

    List<OrderItem> getAllByProduct(Product product);

    @Query("select (sum(o.quantity)) from OrderItem o where o.product.id = ?1")
    Integer countTimesSold(Integer prodId);

    @Query("from OrderItem o where o.product.user.id=?1")
    List<OrderItem> getAllOrderItemsBySellerId(Integer sellerId);

    List<OrderItem> findOrderItemsByProductAndOrderIn(Product product, Collection<Order> order);
}
