package com.store.repository;

import com.store.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;

@Repository
public interface CategoryRepo extends JpaRepository<Category, Integer> {


}
