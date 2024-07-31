package com.incetutku.foodcatalogueservice.service;

import com.incetutku.foodcatalogueservice.dto.FoodCataloguePage;
import com.incetutku.foodcatalogueservice.dto.FoodItemDTO;
import com.incetutku.foodcatalogueservice.entity.FoodItem;
import com.incetutku.foodcatalogueservice.mapper.FoodItemMapper;
import com.incetutku.foodcatalogueservice.repository.FoodItemRepository;
import org.springframework.stereotype.Service;

@Service
public class FoodCatalogueService {
    private final FoodItemRepository foodItemRepository;

    public FoodCatalogueService(FoodItemRepository foodItemRepository) {
        this.foodItemRepository = foodItemRepository;
    }

    public FoodItemDTO createFoodItem(FoodItemDTO foodItemDTO) {
        FoodItem savedFoodItem = foodItemRepository.save(FoodItemMapper.INSTANCE.mapFoodItemDTOToFoodItem(foodItemDTO));
        return FoodItemMapper.INSTANCE.mapFoodItemToFoodItemDTO(savedFoodItem);
    }

    public FoodCataloguePage getFoodCataloguePageDetails(Integer restaurantId) {
        // Food Item List
        // Restaurant Details
        // TODO
        return null;
    }
}
