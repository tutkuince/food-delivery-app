package com.incetutku.foodcatalogueservice.controller;

import com.incetutku.foodcatalogueservice.dto.FoodCataloguePage;
import com.incetutku.foodcatalogueservice.dto.FoodItemDTO;
import com.incetutku.foodcatalogueservice.service.FoodCatalogueService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class FoodCatalogueControllerTest {

    @InjectMocks
    FoodCatalogueController foodCatalogueController;

    @Mock
    FoodCatalogueService foodCatalogueService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateFoodItem() {
        // Arrange
        FoodItemDTO foodItemDTO = new FoodItemDTO();
        when(foodCatalogueService.createFoodItem(any(FoodItemDTO.class))).thenReturn(foodItemDTO);

        // Act
        ResponseEntity<FoodItemDTO> response = foodCatalogueController.createFoodItem(foodItemDTO);

        // Assert
        verify(foodCatalogueService, times(1)).createFoodItem(foodItemDTO);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(response.getBody(), foodItemDTO);

        // Verify that the repository method was called
        verify(foodCatalogueService, times(1)).createFoodItem(foodItemDTO);
    }

    @Test
    void testGetRestaurantDetailsWithFoodMenu() {
        // Arrange
        int restaurantId = 123;
        FoodCataloguePage foodCataloguePage = new FoodCataloguePage();
        when(foodCatalogueService.getFoodCataloguePageDetails(restaurantId)).thenReturn(foodCataloguePage);

        // Act
        ResponseEntity<FoodCataloguePage> response = foodCatalogueController.getRestaurantDetailsWithFoodMenu(restaurantId);

        // Assert
        verify(foodCatalogueService, times(1)).getFoodCataloguePageDetails(restaurantId);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(response.getBody(), foodCataloguePage);

        // Verify that the repository method was called
        verify(foodCatalogueService, times(1)).getFoodCataloguePageDetails(restaurantId);
    }
}