package com.incetutku.orderservice.service;

import com.incetutku.orderservice.dto.CreateOrderDTO;
import com.incetutku.orderservice.dto.OrderDTO;
import com.incetutku.orderservice.dto.UserDTO;
import com.incetutku.orderservice.entity.Order;
import com.incetutku.orderservice.mapper.OrderMapper;
import com.incetutku.orderservice.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

class OrderServiceTest {

    @InjectMocks
    OrderService orderService;

    @Mock
    OrderRepository orderRepository;

    @Mock
    private RestTemplate restTemplate;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveOrder() {
        // Arrange
        CreateOrderDTO orderDetails = new CreateOrderDTO();
        String newOrderId = "1123";
        UserDTO userDTO = new UserDTO();
        Order orderToBeSaved = new Order(newOrderId, orderDetails.getRestaurant(), userDTO, orderDetails.getFoodItemList());
        OrderDTO orderDTOExpected = OrderMapper.INSTANCE.mapOrderToOrderDTO(orderToBeSaved);

        when(orderRepository.save(orderToBeSaved)).thenReturn(orderToBeSaved);
        when(restTemplate.getForObject(anyString(), any())).thenReturn(userDTO);

        // Act
        OrderDTO orderDTOActual = orderService.saveOrder(orderDetails);

        // Assert
        assertDoesNotThrow(() -> orderService.saveOrder(orderDetails));
    }
}