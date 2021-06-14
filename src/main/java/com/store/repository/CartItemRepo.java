package com.store.repository;


import com.store.model.CartItem;
import com.store.model.CartItemId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemRepo extends JpaRepository<CartItem, CartItemId> {

    @Query("from CartItem c  where c.user.id = ?1")
    List<CartItem> getAllCartItemsByUserId(Integer userId);

    @Modifying
    @Query("delete  from CartItem  c where c.user.id = ?1")
    void deleteAllByUserId(Integer userId);

}
