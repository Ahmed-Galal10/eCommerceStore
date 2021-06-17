package com.store.controller;

import com.store.dtos.GenericResponse;
import com.store.dtos.category.CategoryDto;
import com.store.dtos.category.SubCategoryDto;
import com.store.service.CategoryService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
@CrossOrigin
public class CategoryController {

    private CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService){
        this.categoryService = categoryService;
    }


    @GetMapping
    public ResponseEntity< GenericResponse< List<CategoryDto> > > getAllCategories(){

        List<CategoryDto> categoryDtos = categoryService.getAllCategories();

        GenericResponse<List<CategoryDto>> response =
                new GenericResponse<>( categoryDtos, HttpStatus.ACCEPTED, "Request Success" );

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{categoryId}")
    public  ResponseEntity< GenericResponse< List< SubCategoryDto > > > getSubCategory(@PathVariable("categoryId") int categoryId ){

        List<SubCategoryDto> subCategoryDtos = categoryService.getSubCategoriesByCategoryId(categoryId);

        GenericResponse<List<SubCategoryDto>> response =
                new GenericResponse<>( subCategoryDtos, HttpStatus.ACCEPTED, "Request Success" );

        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity< GenericResponse >  createCategory(@RequestBody CategoryDto category){

        CategoryDto categoryDto = categoryService.createCategory(category);

        GenericResponse<CategoryDto> response =
                new GenericResponse<>(categoryDto, HttpStatus.CREATED, "CATTEGORY CREATED");

        return  ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    //WHERE TO POST SUBCATEGORY



}
