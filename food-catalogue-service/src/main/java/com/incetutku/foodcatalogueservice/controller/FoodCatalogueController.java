package com.incetutku.foodcatalogueservice.controller;

import com.incetutku.foodcatalogueservice.dto.FoodCataloguePage;
import com.incetutku.foodcatalogueservice.dto.FoodItemDTO;
import com.incetutku.foodcatalogueservice.service.FoodCatalogueService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/foodCatalogues")
public class FoodCatalogueController {

    private final FoodCatalogueService foodCatalogueService;

    public FoodCatalogueController(FoodCatalogueService foodCatalogueService) {
        this.foodCatalogueService = foodCatalogueService;
    }

    @PostMapping("/addFoodItem")
    public ResponseEntity<FoodItemDTO> createFoodItem(@RequestBody FoodItemDTO foodItemDTO) {
        FoodItemDTO savedFoodItem = foodCatalogueService.createFoodItem(foodItemDTO);
        return new ResponseEntity<>(savedFoodItem, HttpStatus.CREATED);
    }

    @GetMapping("/getRestaurantAndFoodItemsById/{restaurantId}")
    public ResponseEntity<FoodCataloguePage> getRestaurantDetailsWithFoodMenu(@PathVariable Integer restaurantId) {
        FoodCataloguePage foodCataloguePage = foodCatalogueService.getFoodCataloguePageDetails(restaurantId);
        return ResponseEntity.ok(foodCataloguePage);
    }
}
