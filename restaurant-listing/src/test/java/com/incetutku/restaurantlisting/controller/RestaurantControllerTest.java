package com.incetutku.restaurantlisting.controller;

import com.incetutku.restaurantlisting.dto.RestaurantDTO;
import com.incetutku.restaurantlisting.service.RestaurantService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RestaurantControllerTest {

    @InjectMocks
    RestaurantController restaurantController;

    @Mock
    RestaurantService restaurantService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllRestaurants() {
        // Mock the service behavior
        List<RestaurantDTO> mockRestaurants = Arrays.asList(
                new RestaurantDTO(1, "Restaurant 1", "Address 1", "City 1", "Description 1"),
                new RestaurantDTO(1, "Restaurant 2", "Address 2", "City 2", "Description 2")
        );
        when(restaurantService.findAllRestaurants()).thenReturn(mockRestaurants);

        // call the controller method
        ResponseEntity<List<RestaurantDTO>> response = restaurantController.getAllRestaurants();

        // Verify the response
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockRestaurants, response.getBody());

        // verify that the service method was called
        verify(restaurantService, times(1)).findAllRestaurants();
    }

    @Test
    void testGetRestaurantById() {
        // Create a mock restaurant Id
        int mockRestaurantId = 1;

        // Create a mock restaurant to be saved
        RestaurantDTO mockRestaurant = new RestaurantDTO(1, "Restaurant 1", "Address 1", "City 1", "Description 1");

        // Mock the service behavior
        when(restaurantService.findRestaurantById(mockRestaurantId)).thenReturn(mockRestaurant);

        // Call the controller method
        ResponseEntity<RestaurantDTO> response = restaurantController.getRestaurantById(mockRestaurantId);

        // Verify the response
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockRestaurant, response.getBody());

        // Verify that the service method was called
        verify(restaurantService, times(1)).findRestaurantById(mockRestaurantId);
    }

    @Test
    void testSaveRestaurant() {
        // Create a mock restaurant to be saved
        RestaurantDTO mockRestaurant = new RestaurantDTO(1, "Restaurant 1", "Address 1", "City 1", "Description 1");

        // Mock the service behavior
        when(restaurantService.createRestaurant(mockRestaurant)).thenReturn(mockRestaurant);

        // Call the controller method
        ResponseEntity<RestaurantDTO> response = restaurantController.saveRestaurant(mockRestaurant);

        // Verify the response
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(mockRestaurant, response.getBody());

        // Verify that the service method was called
        verify(restaurantService, times(1)).createRestaurant(mockRestaurant);
    }
}