package com.store.service.impl;

import com.store.dtos.category.CategoryDto;
import com.store.dtos.category.SubCategoryDto;
import com.store.model.Category;
import com.store.model.Subcategory;
import com.store.repository.CategoryRepo;
import com.store.service.CategoryService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;


@SpringBootTest
class CategoryImplTest {


    @Mock
    private CategoryRepo repo;

    @InjectMocks
    private final CategoryService service = new CategoryImpl(repo);

    private List<Category> getCategories() {
        List<Category> categories = new ArrayList<>();
        categories.add(getCategory());


        return categories;
    }

    private Category getCategory() {
        Category cate = new Category();
        cate.setId(1);
        cate.setName("electronics");
        cate.setSubcategories(getSubCategories(cate));
        return cate;
    }

    private Set<Subcategory> getSubCategories(Category category) {
        Set<Subcategory> subcategories = new HashSet<>();
        Subcategory sub1 = new Subcategory();
        sub1.setCategory(category);
        sub1.setId(1);
        sub1.setName("laptops");
        subcategories.add(sub1);

        return subcategories;

    }

    private List<CategoryDto> getAllCategoriesDtos() {

        List<CategoryDto> categories = new ArrayList<>();
        categories.add(getCategoryDto());


        return categories;

    }

    private CategoryDto getCategoryDto() {
        CategoryDto cate = new CategoryDto();
        cate.setId(1);
        cate.setName("electronics");
        cate.setSubCategories(getSubCategoriesDtos(cate));
        return cate;
    }

    private List<SubCategoryDto> getSubCategoriesDtos(CategoryDto dto) {
        List<SubCategoryDto> subDtos = new ArrayList<>();
        SubCategoryDto sub1 = new SubCategoryDto();
        sub1.setCategoryId(dto.getId());
        sub1.setId(1);
        sub1.setName("laptops");
        subDtos.add(sub1);
        return subDtos;
    }


    @BeforeEach
    void setMockOutput() {

        Mockito
                .when(repo.findAll())
                .thenReturn(getCategories());

        Mockito
                .when(repo.findById(1))
                .thenReturn(Optional.of(getCategory()));
//        Mockito.when(service.getSubCategoriesByCategoryId(1)).thenReturn(getSubCategoriesDtos(getCategoryDto()));
    }


    @Test
    void getAllCategories() {

        CategoryDto expected = getAllCategoriesDtos().get(0);

        CategoryDto provided = service.getAllCategories().get(0);

        Assertions.assertThat(expected.getName())
                .isNotNull()
                .isEqualTo(provided.getName());

    }

    @Test
    void getSubCategoriesByCategoryId() {

        SubCategoryDto expected = getSubCategoriesDtos(getCategoryDto()).get(0);

        SubCategoryDto provided = service.getSubCategoriesByCategoryId(getCategoryDto().getId()).get(0);

        Assertions.assertThat(expected.getCategoryId())
                .isNotNull()
                .isEqualTo(provided.getCategoryId());
    }
}