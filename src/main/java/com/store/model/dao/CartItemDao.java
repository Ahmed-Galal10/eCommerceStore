package com.store.model.dao;

import com.store.model.entities.CartItem;
import com.store.model.entities.CartItemId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemDao extends JpaRepository<CartItem, CartItemId> {
}
