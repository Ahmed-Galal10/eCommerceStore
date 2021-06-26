package com.store.service.impl;

import com.store.dtos.order.OrderDto;
import com.store.dtos.order.OrderItemDto;
import com.store.dtos.order.OrderItemRequest;
import com.store.dtos.order.OrderRequest;
import com.store.dtos.product.ProdSoldData;
import com.store.dtos.solditem.SoldItemDto;
import com.store.model.*;
import com.store.repository.*;
import com.store.service.OrderService;
import com.store.util.mappers.order.OrderItemMapper;
import com.store.util.mappers.EntityDtoMapper;
import com.store.util.mappers.order.OrderMapper;
import com.store.util.mappers.solditem.SoldItemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.parser.Entity;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

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

    @Autowired
    private SoldItemRepo soldItemRepo;

    public OrderDto getOrderDetail(int orderId) {

        Optional<Order> o = orderRepo.findById(orderId);

        //TODO REFACTOR=> HANDLE ERR
        Order order = o.orElse(null);
        if (order == null) {

        }

        EntityDtoMapper<Order, OrderDto> mapper = new OrderMapper();

        OrderDto orderDto = mapper.toDto(order);
        return orderDto;
    }

    @Override
    public OrderDto createOrder(OrderRequest orderRequest) {

        //TODO REFACTOR => CartItems <---> Order
        List<CartItem> cartItems = cartItemRepo.getAllCartItemsByUserId(orderRequest.getCustomerId());
        Customer customer = customerRepo.getOne(orderRequest.getCustomerId());
        Order order = new Order();
        Set<OrderItem> orderItems = new HashSet<>();

        order.setUser(customer);
        order.setDate(new Date());

        cartItems.stream().forEach(cartItem -> {

            OrderItem orderItem = new OrderItem();
            Product prod = cartItem.getProduct();

            orderItem.setProduct(prod);
            orderItem.setUnitPrice(prod.getPrice());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setOrder(order);
            orderItems.add(orderItem);
        });
        var soldItemSet = orderItems.stream().map(orderItem -> {
            SoldItem soldItem = new SoldItem();
            soldItem.setOrderItem(orderItem);
            soldItem.setOrder(order);
            soldItem.setUser(orderItem.getProduct().getUser());
            return soldItem;
        }).collect(Collectors.toSet());
        order.setOrderItems(orderItems);
//        order.setSoldItems(soldItemSet);
        orderRepo.save(order);
//        soldItemRepo.saveAll(soldItemSet);
        cartItemRepo.deleteAllByUserId(customer.getId());

        EntityDtoMapper<Order, OrderDto> mapper = new OrderMapper();
        return mapper.toDto(order);
    }

    @Override
    public List<ProdSoldData> getProdSoldData(Integer prodId) {

        List<ProdSoldData> prodSoldData = new ArrayList<>();
        Product product = productRepo.getOne(prodId);

        List<OrderItem> orderItems = orderItemRepo.getAllByProduct(product);

        orderItems.stream().forEach(orderItem -> {
            try {
                ProdSoldData soldData = new ProdSoldData();

                Date soldDate = orderItem.getOrder().getDate();
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                String date = dateFormat.format(soldDate);

                soldData.setSoldDate( dateFormat.parse(date) );
                soldData.setOrderId( orderItem.getOrder().getId() );
                soldData.setSoldQuantity( orderItem.getQuantity() );

                prodSoldData.add( soldData );

            } catch (ParseException e) {
                e.printStackTrace();
            }
        });
        return  prodSoldData;
    }

    @Transactional
    @Override
    public List<SoldItemDto> getSoldItemsBySeller(Integer sellerId) {


        List<OrderItem> soldItems = orderItemRepo.getAllOrderItemsBySellerId(sellerId);

        EntityDtoMapper<OrderItem, SoldItemDto> mapper = new SoldItemMapper();

        List<SoldItemDto> soldItemDtoList = mapper.entityListToDtoList( soldItems ) ;
        return soldItemDtoList;
    }
}
