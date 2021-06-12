package com.store.util.mappers;

import com.store.dtos.category.CategoryDto;
import com.store.dtos.category.SubCategoryDto;
import com.store.dtos.product.ProdDetailDto;
import com.store.dtos.seller.ProductDto;
import com.store.model.Category;
import com.store.model.Product;
import com.store.model.Subcategory;
import com.store.model.User;
import org.springframework.stereotype.Service;

@Service
public class ProductDetailsMapper extends EntityDtoMapper<Product, ProdDetailDto> {
    @Override
    public ProdDetailDto toDto(Product entity) {
        ProdDetailDto dto = new ProdDetailDto();
        dto.setProductName(entity.getName());
        dto.setProductDescription(entity.getDescription());
        dto.setId(entity.getId());
        dto.setProductImg(entity.getImg());
        dto.setProductPrice(entity.getPrice());
        dto.setOnSale(entity.isIsOnSale());
        dto.setProductQuantity(entity.getQuantity());
        dto.setSubcategoryId(entity.getSubcategory().getId());
        return dto;
    }


    @Override
    public Product toEntity(ProdDetailDto dto) {
        Product product = new Product();
        Subcategory subCategory = new Subcategory();
        Category category= new Category();
        User user = new User();

        product.setId(dto.getId());
        product.setName(dto.getProductName());
        product.setDescription(dto.getProductDescription());
        product.setQuantity(dto.getProductQuantity());
        product.setImg(dto.getProductImg());
        product.setPrice(dto.getProductPrice());
        product.setIsOnSale(dto.isOnSale());
        // Todo add mapping to category

        // Todo implement a user DTO for this product, implement mapper for it
        user.setId(dto.getUserId());;
        subCategory.setId(dto.getSubcategoryId());
        subCategory.setName(dto.getSubcategoryDto().getName());

        category.setId(dto.getCategoryDTO().getId());
        category.setName(dto.getCategoryDTO().getName());

        subCategory.setCategory(category);

        product.setSubcategory(subCategory);
        product.setUser(user);
        return product;


    }
}
