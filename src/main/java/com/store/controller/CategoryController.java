package com.store.controller;

import com.store.dtos.category.CategoryDto;
import com.store.dtos.category.SubCategoryDto;
import com.store.service.CategoryService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    private CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService){
        this.categoryService = categoryService;
    }


    @GetMapping
    public ResponseEntity<List<CategoryDto>> getAllCategories(){

        List<CategoryDto> categoryDtos = categoryService.getAllCategories();

        return ResponseEntity.ok(categoryDtos);
    }


    @GetMapping("/{categoryId}/sub-categories")
    public  ResponseEntity<List<SubCategoryDto>> getSubCategory(@PathVariable("categoryId") int categoryId ){

        List<SubCategoryDto> subCategoryDtos = categoryService.getSubCategoriesByCategoryId(categoryId);

        return ResponseEntity.ok(subCategoryDtos);
    }


}
