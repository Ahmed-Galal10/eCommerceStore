package com.store.service;

import com.store.dtos.category.CategoryDto;
import com.store.dtos.category.SubCategoryDto;
import org.springframework.stereotype.Service;

import java.util.List;


public interface CategoryService {


    List<CategoryDto> getAllCategories();

    List<SubCategoryDto> getSubCategoriesByCategoryId(Integer categoryId);

}
