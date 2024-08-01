package com.incetutku.orderservice.entity;

import com.incetutku.orderservice.dto.FoodItemDTO;
import com.incetutku.orderservice.dto.RestaurantDTO;
import com.incetutku.orderservice.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document("order")
public class Order {
    private String orderId;
    private RestaurantDTO restaurant;
    private UserDTO user;
    private List<FoodItemDTO> foodItemList;
}
