package com.incetutku.orderservice.dto;

import java.util.List;

public class OrderDTO {
    private String orderId;
    private RestaurantDTO restaurant;
    private UserDTO user;
    private List<FoodItemDTO> foodItemList;
}
