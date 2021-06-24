package com.store.dtos.customer;

import com.store.dtos.order.OrderDto;

import java.util.List;

public class CustomerDetailsDto {

    private List<OrderDto> orderDtoList;
    private CustomerDto customerDto;

    public CustomerDetailsDto() {
    }

    public CustomerDetailsDto(CustomerDto customerDto,List<OrderDto> orderDtoList) {
        this.orderDtoList = orderDtoList;
        this.customerDto = customerDto;
    }


    public CustomerDto getCustomerDto() {
        return customerDto;
    }

    public void setCustomerDto(CustomerDto customerDto) {
        this.customerDto = customerDto;
    }

    public List<OrderDto> getOrderDtoList() {
        return orderDtoList;
    }

    public void setOrderDtoList(List<OrderDto> orderDtoList) {
        this.orderDtoList = orderDtoList;
    }
}
