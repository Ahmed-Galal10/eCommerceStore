package com.store.util.mappers;

import com.store.model.CartItem;
import com.store.model.Product;
import com.store.dtos.cart.CartItemDto;

public class CartItemMapper extends EntityDtoMapper<CartItem, CartItemDto> {


    @Override
    public CartItemDto toDto(CartItem entity) {

        CartItemDto cartItemDto = new CartItemDto();
        Product product = entity.getProduct();

        cartItemDto.setProductId(product.getId());
        cartItemDto.setName(product.getName());
        cartItemDto.setPrice(product.getPrice());
        cartItemDto.setQuantity(entity.getQuantity());
        cartItemDto.setImg(product.getImg());

        return  cartItemDto;

    }

    @Override
    public CartItem toEntity(CartItemDto dto) {
        return null;
    }
}
