package com.incetutku.orderservice.controller;

import com.incetutku.orderservice.dto.CreateOrderDTO;
import com.incetutku.orderservice.dto.OrderDTO;
import com.incetutku.orderservice.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<OrderDTO> createOrder(@RequestBody CreateOrderDTO createOrder) {
        OrderDTO orderDTO = orderService.saveOrder(createOrder);
        return new ResponseEntity<>(orderDTO, HttpStatus.CREATED);
    }
}
