package com.store.util.mappers;

import com.store.dtos.customer.CustomerOrderDto;
import com.store.model.Customer;
import com.store.model.Order;
import com.store.model.OrderItem;

public class CustomerOrderMapper extends EntityDtoMapper<Order, CustomerOrderDto>{

    @Override
    public CustomerOrderDto toDto(Order entity) {

        CustomerOrderDto customerOrderDto = new CustomerOrderDto();

        customerOrderDto.setId(entity.getId());
        customerOrderDto.setDate(entity.getDate());
        customerOrderDto.setOrderItems(entity.getOrderItems());

        return customerOrderDto;
    }

    @Override
    public Order toEntity(CustomerOrderDto dto) {

        Order order = new Order();

        order.setId(dto.getId());
        order.setDate(dto.getDate());
        order.setOrderItems(dto.getOrderItems());

        return order;
    }
}
