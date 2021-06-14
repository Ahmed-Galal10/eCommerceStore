package com.store.service;

import com.store.dtos.customer.CustomerOrderDto;
import com.store.dtos.order.OrderDto;
import com.store.dtos.order.OrderRequest;

import java.util.List;

public interface OrderService {

     OrderDto getOrderDetail(int orderId);


     OrderDto createOrder(OrderRequest orderRequest);
//     CustomerOrderDto createOrder(OrderRequest orderRequest);

}
