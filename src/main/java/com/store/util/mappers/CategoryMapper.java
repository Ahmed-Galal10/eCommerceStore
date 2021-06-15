package com.store.util.mappers;

import com.store.dtos.category.CategoryDto;
import com.store.dtos.category.SubCategoryDto;
import com.store.model.Category;
import com.store.model.Subcategory;

import java.util.ArrayList;
import java.util.List;

public class CategoryMapper extends  EntityDtoMapper<Category, CategoryDto>{
    @Override
    public CategoryDto toDto(Category entity) {

        CategoryDto categoryDto = new CategoryDto();

        categoryDto.setId(entity.getId());
        categoryDto.setName(entity.getName());

        List<Subcategory> subcategories = new ArrayList<>( entity.getSubcategories() );
        EntityDtoMapper<Subcategory, SubCategoryDto> mapper = new SubCategoryMapper();
        List<SubCategoryDto> subCategoryDtos =  mapper.entityListToDtoList(subcategories);

        categoryDto.setSubCategories(subCategoryDtos);

        return  categoryDto;
    }

    @Override
    public Category toEntity(CategoryDto dto) {
        return null;
    }
}
