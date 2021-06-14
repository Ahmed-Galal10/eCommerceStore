package com.store.util.mappers;

import com.store.dtos.customer.CustomerOrderDto;
import com.store.model.Order;
import com.store.model.OrderItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class CustomerOrderMapper extends EntityDtoMapper<Order, CustomerOrderDto>{

    @Override
    public CustomerOrderDto toDto(Order entity) {

        CustomerOrderDto customerOrderDto = new CustomerOrderDto();

        customerOrderDto.setId(entity.getId());
        customerOrderDto.setDate(entity.getDate());

        Set<OrderItem> orderItemList =  entity.getOrderItems();

        customerOrderDto.setOrderItems(new ArrayList<>(orderItemList));

        return customerOrderDto;
    }

    @Override
    public Order toEntity(CustomerOrderDto dto) {

//        Order order = new Order();
//
//        order.setId(dto.getId());
//        order.setDate(dto.getDate());
//        order.setOrderItems(dto.getOrderItems());

        return null;
    }
}
