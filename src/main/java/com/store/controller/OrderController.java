package com.store.controller;


import com.store.dtos.GenericResponse;
import com.store.dtos.customer.CustomerDto;
import com.store.dtos.order.OrderDto;
import com.store.dtos.order.OrderRequest;
import com.store.service.OrderService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/orders")
public class OrderController {

    private OrderService orderService;

    @Autowired
    public  OrderController(OrderService orderService){
        this.orderService = orderService;
    }

    @GetMapping(path = "/{orderId}")
    public ResponseEntity<GenericResponse <OrderDto> > getOrderDetail(@PathVariable("orderId") int orderId){

        OrderDto orderDto = orderService.getOrderDetail(orderId);
        GenericResponse<OrderDto> response =
                new GenericResponse<>(orderDto, HttpStatus.OK, "REQUEST SUCCESSFUL");

        return ResponseEntity.ok(response);
    }

    @GetMapping()
    public ResponseEntity<GenericResponse<List<OrderDto>>> getAllOrders() {
        try {
            List<OrderDto> orderDtoList = orderService.getAllOrders();
            GenericResponse<List<OrderDto>> response =
                    new GenericResponse<>(orderDtoList, HttpStatus.OK, "REQUEST SUCCESSFUL");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok(new GenericResponse<>(null, HttpStatus.BAD_REQUEST, e.getMessage()));
        }
    }

}
