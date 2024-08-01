package com.incetutku.orderservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateOrderDTO {
    private List<FoodItemDTO> foodItemList;
    private Integer userId;
    private RestaurantDTO restaurant;
}
