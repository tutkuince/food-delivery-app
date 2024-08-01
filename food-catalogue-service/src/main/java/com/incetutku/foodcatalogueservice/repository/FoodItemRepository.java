package com.incetutku.foodcatalogueservice.repository;

import com.incetutku.foodcatalogueservice.entity.FoodItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FoodItemRepository extends JpaRepository<FoodItem, Integer> {
    List<FoodItem> findFoodItemsByRestaurantId(int restaurantId);
}
