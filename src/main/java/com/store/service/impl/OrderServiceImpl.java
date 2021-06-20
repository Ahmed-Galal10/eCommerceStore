package com.store.service.impl;

import com.store.dtos.order.OrderDto;
import com.store.dtos.order.OrderItemDto;
import com.store.dtos.order.OrderItemRequest;
import com.store.dtos.order.OrderRequest;
import com.store.model.*;
import com.store.repository.*;
import com.store.service.OrderService;
import com.store.util.mappers.order.OrderItemMapper;
import com.store.util.mappers.EntityDtoMapper;
import com.store.util.mappers.order.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.parser.Entity;
import java.util.*;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepo orderRepo;
    @Autowired
    private CustomerRepo customerRepo;
    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private OrderItemRepo orderItemRepo;
    @Autowired
    private CartItemRepo cartItemRepo;

    public OrderDto getOrderDetail(int orderId){

        Optional<Order> o = orderRepo.findById( orderId );

        //TODO REFACTOR=> HANDLE ERR
        Order order = o.orElse(null);
        if(order == null){

        }

        EntityDtoMapper<Order, OrderDto> mapper = new OrderMapper();

        OrderDto orderDto = mapper.toDto( order );
        return  orderDto;
    }

    @Override
    public OrderDto createOrder(OrderRequest orderRequest) {

        //TODO REFACTOR => CartItems <---> Order
        List<CartItem> cartItems =  cartItemRepo.getAllCartItemsByUserId(orderRequest.getCustomerId());
        Customer customer = customerRepo.getOne( orderRequest.getCustomerId() );
        Order order = new Order();
        Set<OrderItem> orderItems = new HashSet<>();

        order.setUser( customer );
        order.setDate( new Date() );

        cartItems.stream().forEach(cartItem ->  {

            OrderItem orderItem = new OrderItem();
            Product prod = cartItem.getProduct();

            orderItem.setProduct( prod );
            System.out.println();
            orderItem.setUnitPrice( prod.getPrice() );
            orderItem.setQuantity( cartItem.getQuantity() );
            orderItem.setOrder(order);
            orderItems.add( orderItem );

        });
//        order = orderRepo.saveAndFlush( order );
//        System.out.println(order.getId());
//
//        for (OrderItemRequest itemRequest : orderRequest.getOrderItems()){
//
//            OrderItem orderItem = new OrderItem();
//            Product prod = productRepo.getOne( itemRequest.getProductId() );
//
//            orderItem.setProduct( prod );
//            System.out.println();
//            orderItem.setUnitPrice( prod.getPrice() );
//            orderItem.setQuantity( itemRequest.getQuantity() );
//            orderItem.setOrder(order);
//            orderItems.add( orderItem );
//        }

        order.setOrderItems( orderItems );

        orderRepo.save(order);

        cartItemRepo.deleteAllByUserId(customer.getId());

        EntityDtoMapper<Order, OrderDto> mapper = new OrderMapper();
        return mapper.toDto(order);
    }
}
