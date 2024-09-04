package com.incetutku.foodcatalogueservice.service;

import com.incetutku.foodcatalogueservice.dto.FoodCataloguePage;
import com.incetutku.foodcatalogueservice.dto.FoodItemDTO;
import com.incetutku.foodcatalogueservice.dto.Restaurant;
import com.incetutku.foodcatalogueservice.entity.FoodItem;
import com.incetutku.foodcatalogueservice.mapper.FoodItemMapper;
import com.incetutku.foodcatalogueservice.repository.FoodItemRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class FoodCatalogueServiceTest {

    @InjectMocks
    FoodCatalogueService foodCatalogueService;

    @Mock
    FoodItemRepository foodItemRepository;

    @Mock
    private RestTemplate restTemplate;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateFoodItem() {
        // Arrange
        FoodItemDTO foodItemDTO = new FoodItemDTO();
        FoodItem foodItem = new FoodItem();
        when(foodItemRepository.save(any(FoodItem.class))).thenReturn(foodItem);

        // Act
        FoodItemDTO result = foodCatalogueService.createFoodItem(foodItemDTO);

        // Assert
        verify(foodItemRepository, times(1)).save(any(FoodItem.class));
        assertEquals(FoodItemMapper.INSTANCE.mapFoodItemToFoodItemDTO(foodItem), result);
    }

    @Test
    void testGetFoodCataloguePageDetails() {
        // Arrange
        int restaurantId = 123;
        List<FoodItem> foodItemList = Collections.singletonList(new FoodItem());
        Restaurant restaurant = new Restaurant();
        when(foodItemRepository.findFoodItemsByRestaurantId(restaurantId)).thenReturn(foodItemList);
        when(restTemplate.getForObject(anyString(), eq(Restaurant.class))).thenReturn(restaurant);

        // Act
        FoodCataloguePage result = foodCatalogueService.getFoodCataloguePageDetails(restaurantId);

        // Assert
        verify(foodItemRepository, times(1)).findFoodItemsByRestaurantId(restaurantId);
        verify(restTemplate, times(1)).getForObject(anyString(), eq(Restaurant.class));
        Assertions.assertEquals(foodItemList, result.getFoodItemList());
        Assertions.assertEquals(restaurant, result.getRestaurant());
    }
}