package com.incetutku.foodcatalogueservice.service;

import com.incetutku.foodcatalogueservice.repository.FoodItemRepository;
import org.springframework.stereotype.Service;

@Service
public class FoodCatalogueService {
    private final FoodItemRepository foodItemRepository;

    public FoodCatalogueService(FoodItemRepository foodItemRepository) {
        this.foodItemRepository = foodItemRepository;
    }
}
