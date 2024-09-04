package com.incetutku.orderservice.controller;

import com.incetutku.orderservice.dto.CreateOrderDTO;
import com.incetutku.orderservice.dto.OrderDTO;
import com.incetutku.orderservice.service.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class OrderControllerTest {

    @InjectMocks
    OrderController orderController;

    @Mock
    OrderService orderService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateOrder() {
        // Arrange
        CreateOrderDTO orderDetails = new CreateOrderDTO();
        OrderDTO orderSavedInDB = new OrderDTO();
        when(orderService.saveOrder(orderDetails)).thenReturn(orderSavedInDB);

        // Act
        ResponseEntity<OrderDTO> response = orderController.createOrder(orderDetails);

        // Assert
        verify(orderService, times(1)).saveOrder(orderDetails);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(orderSavedInDB, response.getBody());
    }
}