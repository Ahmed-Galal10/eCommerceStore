package com.store.service;

import com.store.dtos.customer.CustomerOrderDto;
import com.store.dtos.order.OrderDto;
import com.store.dtos.order.OrderRequest;
import com.store.dtos.product.ProdSoldData;
import com.store.dtos.solditem.SoldItemDto;

import java.util.List;

public interface OrderService {

    OrderDto getOrderDetail(int orderId);

    OrderDto createOrder(OrderRequest orderRequest);

    List<ProdSoldData> getProdSoldData(Integer prodId);

    List<SoldItemDto> getSoldItemsBySeller(Integer sellerId);

    List<SoldItemDto> getAllSoldItems();

    List<OrderDto> getAllOrders();
}
