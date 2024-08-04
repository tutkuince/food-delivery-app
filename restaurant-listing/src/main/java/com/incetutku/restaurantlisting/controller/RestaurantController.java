package com.incetutku.restaurantlisting.controller;

import com.incetutku.restaurantlisting.dto.RestaurantDTO;
import com.incetutku.restaurantlisting.service.RestaurantService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/restaurants")
public class RestaurantController {

    private final RestaurantService restaurantService;

    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @GetMapping
    public ResponseEntity<List<RestaurantDTO>> getAllRestaurants() {
        List<RestaurantDTO> restaurantDTOList = restaurantService.findAllRestaurants();
        return ResponseEntity.ok(restaurantDTOList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RestaurantDTO> getRestaurantById(@PathVariable int id) {
        RestaurantDTO restaurantById = restaurantService.findRestaurantById(id);
        if (restaurantById != null) return ResponseEntity.ok(restaurantById);
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<RestaurantDTO> saveRestaurant(@RequestBody RestaurantDTO restaurantDTO) {
        RestaurantDTO savedRestaurant = restaurantService.createRestaurant(restaurantDTO);
        return new ResponseEntity<>(savedRestaurant, HttpStatus.CREATED);
    }
}
