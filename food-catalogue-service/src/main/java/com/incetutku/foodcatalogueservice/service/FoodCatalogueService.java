package com.incetutku.foodcatalogueservice.service;

import com.incetutku.foodcatalogueservice.dto.FoodCataloguePage;
import com.incetutku.foodcatalogueservice.dto.FoodItemDTO;
import com.incetutku.foodcatalogueservice.dto.Restaurant;
import com.incetutku.foodcatalogueservice.entity.FoodItem;
import com.incetutku.foodcatalogueservice.mapper.FoodItemMapper;
import com.incetutku.foodcatalogueservice.repository.FoodItemRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class FoodCatalogueService {
    private final FoodItemRepository foodItemRepository;
    private final RestTemplate restTemplate;

    public FoodCatalogueService(FoodItemRepository foodItemRepository, RestTemplate restTemplate) {
        this.foodItemRepository = foodItemRepository;
        this.restTemplate = restTemplate;
    }

    public FoodItemDTO createFoodItem(FoodItemDTO foodItemDTO) {
        FoodItem savedFoodItem = foodItemRepository.save(FoodItemMapper.INSTANCE.mapFoodItemDTOToFoodItem(foodItemDTO));
        return FoodItemMapper.INSTANCE.mapFoodItemToFoodItemDTO(savedFoodItem);
    }

    public FoodCataloguePage getFoodCataloguePageDetails(Integer restaurantId) {
        List<FoodItem> foodItemList = getFoodItemList(restaurantId);
        Restaurant restaurant = getRestaurantDetailsFromRestaurantService(restaurantId);
        return createFoodCataloguePage(foodItemList, restaurant);
    }

    private FoodCataloguePage createFoodCataloguePage(List<FoodItem> foodItemList, Restaurant restaurant) {
        FoodCataloguePage foodCataloguePage = new FoodCataloguePage();
        foodCataloguePage.setFoodItemList(foodItemList);
        foodCataloguePage.setRestaurant(restaurant);
        return foodCataloguePage;
    }

    private Restaurant getRestaurantDetailsFromRestaurantService(Integer restaurantId) {
        return restTemplate.getForObject("http://RESTAURANT-SERVICE/restaurants/" + restaurantId, Restaurant.class);
    }

    private List<FoodItem> getFoodItemList(Integer restaurantId) {
        return foodItemRepository.findFoodItemsByRestaurantId(restaurantId);
    }
}
