package com.store.model.dao;

import com.store.model.entities.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubCategoryDao extends JpaRepository<SubCategory,Integer>{
}
