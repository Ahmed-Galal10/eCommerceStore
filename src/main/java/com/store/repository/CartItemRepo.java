package com.store.repository;


import com.store.entities.CartItem;
import com.store.entities.CartItemId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemRepo extends JpaRepository<CartItem, CartItemId> {

    @Query("from CartItem c  where c.user.id = ?1")
    List<CartItem> getAllCartItemsByUserId(Integer userId);

}
