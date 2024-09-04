package com.incetutku.restaurantlisting.service;

import com.incetutku.restaurantlisting.dto.RestaurantDTO;
import com.incetutku.restaurantlisting.entity.Restaurant;
import com.incetutku.restaurantlisting.mapper.RestaurantMapper;
import com.incetutku.restaurantlisting.repository.RestaurantRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RestaurantServiceTest {

    @Mock
    RestaurantRepository restaurantRepository;

    @InjectMocks
    RestaurantService restaurantService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAllRestaurants() {
        // Create mock restaurants
        List<Restaurant> mockRestaurants = Arrays.asList(
                new Restaurant(1, "Restaurant - 1", "Address - 1", "City - 1", "Description - 1"),
                new Restaurant(1, "Restaurant - 2", "Address - 2", "City - 2", "Description - 2")
        );
        when(restaurantRepository.findAll()).thenReturn(mockRestaurants);

        // Call the service method
        List<RestaurantDTO> restaurantDTOList = restaurantService.findAllRestaurants();

        // Verify the result
        assertEquals(mockRestaurants.size(), restaurantDTOList.size());
        for (int i = 0; i < mockRestaurants.size(); i++) {
            RestaurantDTO expectedDTO = RestaurantMapper.INSTANCE.mapRestaurantToRestaurantDTO(mockRestaurants.get(i));
            assertEquals(expectedDTO, restaurantDTOList.get(i));
        }

        // Verify that the repository method was called
        verify(restaurantRepository, times(1)).findAll();
    }

    @Test
    void testCreateRestaurant() {
        // Create a mock restaurant to be saved
        RestaurantDTO mockRestaurantDTO = new RestaurantDTO
                (1, "Restaurant - 1", "Address - 1", "City - 1", "Description - 1");
        Restaurant mockRestaurant = RestaurantMapper.INSTANCE.mapRestaurantDTOToRestaurant(mockRestaurantDTO);

        // Mock the repository behaviour
        when(restaurantRepository.save(mockRestaurant)).thenReturn(mockRestaurant);

        // Call the service method
        RestaurantDTO restaurantDTO = restaurantService.createRestaurant(mockRestaurantDTO);

        // Verify the result
        verify(restaurantRepository, times(1)).save(mockRestaurant);
    }

    @Test
    void testFindRestaurantById_ExistingId() {
        // Create a mock restaurant ID
        int mockRestaurantId = 1;

        // Create a mock restaurant to be returned by the repository
        Restaurant mockRestaurant = new Restaurant(1, "Restaurant - 1", "Address - 1", "City - 1", "Description - 1");

        // Mock the repository behavior
        when(restaurantRepository.findById(mockRestaurantId)).thenReturn(Optional.of(mockRestaurant));

        // Call the service method
        RestaurantDTO restaurantById = restaurantService.findRestaurantById(mockRestaurantId);

        // Verify the result
        assertEquals(mockRestaurantId, restaurantById.getId());

        // Verify that the repository method was called
        verify(restaurantRepository, times(1)).findById(mockRestaurantId);
    }

    @Test
    void testFindRestaurantById_NonExistingId() {
        // Create a mock restaurant ID
        int mockRestaurantId = 1;

        // Mock the repository behavior
        when(restaurantRepository.findById(mockRestaurantId)).thenReturn(Optional.empty());

        // Call the service method
        RestaurantDTO restaurantById = restaurantService.findRestaurantById(mockRestaurantId);

        // Verify the result
        assertNull(restaurantById);

        // Verify that the repository method was called
        verify(restaurantRepository, times(1)).findById(mockRestaurantId);
    }
}