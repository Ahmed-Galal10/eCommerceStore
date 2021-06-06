package com.store.service.impl;


import com.store.dtos.category.CategoryDto;
import com.store.dtos.category.SubCategoryDto;
import com.store.model.Category;
import com.store.model.Subcategory;
import com.store.repository.CategoryRepo;
import com.store.service.CategoryService;
import com.store.util.mappers.CategoryMapper;
import com.store.util.mappers.EntityDtoMapper;
import com.store.util.mappers.SubCategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryImpl implements CategoryService {

    CategoryRepo categoryRepo;

    @Autowired
    public CategoryImpl(CategoryRepo categoryRepo){
        this.categoryRepo = categoryRepo;
    }


    @Override
    public List<CategoryDto> getAllCategories() {

        List<Category> categories = categoryRepo.findAll();

        EntityDtoMapper<Category, CategoryDto> mapper = new CategoryMapper();
        List<CategoryDto> categoryDtos = mapper.entityListToDtoList(categories);

        return  categoryDtos;
    }

    @Override
    public List<SubCategoryDto> getSubCategoriesByCategoryId(Integer categoryId) {

        Optional<Category> c = categoryRepo.findById(categoryId);

        //TODO Refactor INTO Err handling
        Category category = c.orElse(null);
        if(category == null){

        }

        List<Subcategory> subcategories = new ArrayList<>( category.getSubcategories() );

        EntityDtoMapper<Subcategory, SubCategoryDto> mapper = new SubCategoryMapper();
        List<SubCategoryDto> subCategoryDtos =  mapper.entityListToDtoList(subcategories);

        return  subCategoryDtos;
    }
}
