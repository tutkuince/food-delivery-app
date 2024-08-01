package com.incetutku.orderservice.service;

import com.incetutku.orderservice.dto.CreateOrderDTO;
import com.incetutku.orderservice.dto.OrderDTO;
import com.incetutku.orderservice.dto.UserDTO;
import com.incetutku.orderservice.entity.Order;
import com.incetutku.orderservice.mapper.OrderMapper;
import com.incetutku.orderservice.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private SequenceGenerator sequenceGenerator;
    private final RestTemplate restTemplate;

    public OrderService(OrderRepository orderRepository, SequenceGenerator sequenceGenerator, RestTemplate restTemplate) {
        this.orderRepository = orderRepository;
        this.sequenceGenerator = sequenceGenerator;
        this.restTemplate = restTemplate;
    }


    public OrderDTO saveOrder(CreateOrderDTO createOrder) {
        String newOrderId = UUID.randomUUID().toString();
        UserDTO user = getUserDetailsByIdFromUserService(createOrder.getUserId());
        Order orderToBeSaved = new Order(
                newOrderId,
                createOrder.getRestaurant(),
                user,
                createOrder.getFoodItemList()
        );
        orderRepository.save(orderToBeSaved);
        return OrderMapper.INSTANCE.mapOrderToOrderDTO(orderToBeSaved);
    }

    private UserDTO getUserDetailsByIdFromUserService(Integer userId) {
        return restTemplate.getForObject("http://USER-SERVICE/users/" + userId, UserDTO.class);
    }
}
