package com.store.repository;

import com.store.model.SoldItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SoldItemRepo extends JpaRepository<SoldItem, Integer> {


}
