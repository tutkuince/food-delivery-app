package com.incetutku.foodcatalogueservice.controller;

import com.incetutku.foodcatalogueservice.service.FoodCatalogueService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/foodCatalogues")
public class FoodCatalogueController {

    private final FoodCatalogueService foodCatalogueService;

    public FoodCatalogueController(FoodCatalogueService foodCatalogueService) {
        this.foodCatalogueService = foodCatalogueService;
    }
}
