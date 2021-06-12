package com.store.dtos.category;

import java.util.List;

public class CategoryDto {

    private int id;
    private String name;

    List<SubCategoryDto> subCategories;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<SubCategoryDto> getSubCategories() {
        return subCategories;
    }

    public void setSubCategories(List<SubCategoryDto> subCategories) {
        this.subCategories = subCategories;
    }

    @Override
    public String toString() {
        return "CategoryDto{" +
                "id=" + id +
                ", name=" + name +
                '}';
    }
}
