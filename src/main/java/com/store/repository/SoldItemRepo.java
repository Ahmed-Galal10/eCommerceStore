package com.store.repository;

import com.store.model.SoldItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SoldItemRepo extends JpaRepository<SoldItem, Integer> {


}
