package com.incetutku.orderservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {
    private String orderId;
    private RestaurantDTO restaurant;
    private UserDTO user;
    private List<FoodItemDTO> foodItemList;
}
