package com.store.repository;

import com.store.model.Subcategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public interface SubCategoryRepo extends JpaRepository<Subcategory, Integer> {
    List<Subcategory> findByIdIn(Collection<Integer> ids);
}
