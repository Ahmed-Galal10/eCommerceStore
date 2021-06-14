package com.store.util.mappers.order;

import com.store.dtos.order.OrderDto;
import com.store.dtos.order.OrderItemDto;
import com.store.model.Order;
import com.store.model.OrderItem;
import com.store.util.mappers.EntityDtoMapper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderMapper extends EntityDtoMapper<Order, OrderDto> {
    @Override
    public OrderDto toDto(Order entity) {

        OrderDto orderDto = new OrderDto();

        orderDto.setId( entity.getId() );
        Date rawDate = entity.getDate();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy, HH:mm");
        orderDto.setDate( dateFormat.format(rawDate) );
        orderDto.setCustomerAddress( entity.getUser().getAddress() );
        orderDto.setCustomerEmail( entity.getUser().getEmail() );
        orderDto.setCustomerName( entity.getUser().getName() );
        orderDto.setCustomerPhone( entity.getUser().getPhone()   );

        List<OrderItem> orderItems = new ArrayList<>( entity.getOrderItems() );

        EntityDtoMapper<OrderItem, OrderItemDto> mapper = new OrderItemMapper();
        List<OrderItemDto> orderItemDtos = mapper.entityListToDtoList( orderItems );

        orderDto.setOrderItems( orderItemDtos );

        return  orderDto;
    }



    @Override
    public Order toEntity(OrderDto dto) {
        return null;
    }
}
