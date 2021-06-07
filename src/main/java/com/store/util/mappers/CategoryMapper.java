package com.store.util.mappers;

import com.store.dtos.category.CategoryDto;
import com.store.model.Category;

public class CategoryMapper extends  EntityDtoMapper<Category, CategoryDto>{
    @Override
    public CategoryDto toDto(Category entity) {

        CategoryDto categoryDto = new CategoryDto();

        categoryDto.setId(entity.getId());
        categoryDto.setName(entity.getName());

        return  categoryDto;
    }

    @Override
    public Category toEntity(CategoryDto dto) {
        return null;
    }
}
