package com.store.util.mappers;

import com.store.dtos.category.SubCategoryDto;
import com.store.model.Subcategory;

public class SubCategoryMapper extends  EntityDtoMapper<Subcategory, SubCategoryDto>{
    @Override
    public SubCategoryDto toDto(Subcategory entity) {

        SubCategoryDto subCategoryDto =  new SubCategoryDto();

        subCategoryDto.setId(entity.getId());
        subCategoryDto.setCategoryId(entity.getCategory().getId());
        subCategoryDto.setName(entity.getName());

        return  subCategoryDto;
    }

    @Override
    public Subcategory toEntity(SubCategoryDto dto) {
        return null;
    }
}
