package com.incetutku.foodcatalogueservice.dto;

import com.incetutku.foodcatalogueservice.entity.FoodItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FoodCataloguePage {

    private List<FoodItem> foodItemList;
    private Restaurant restaurant;
}
