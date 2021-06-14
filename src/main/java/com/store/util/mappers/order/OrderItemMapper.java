package com.store.util.mappers.order;

import com.store.dtos.order.OrderItemDto;
import com.store.model.OrderItem;
import com.store.util.mappers.EntityDtoMapper;

public class OrderItemMapper extends EntityDtoMapper<OrderItem, OrderItemDto> {
    @Override
    public OrderItemDto toDto(OrderItem entity) {

        OrderItemDto itemDto = new OrderItemDto();

        itemDto.setProductId( entity.getProduct().getId() );
        itemDto.setProductName( entity.getProduct().getName() );
        itemDto.setUnitPrice( entity.getUnitPrice() );
        itemDto.setProductImg( entity.getProduct().getImg() );
        itemDto.setQuantity( entity.getQuantity() );

        return  itemDto;
    }

    @Override
    public OrderItem toEntity(OrderItemDto dto) {
        return null;
    }
}
