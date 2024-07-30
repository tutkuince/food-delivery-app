package com.incetutku.foodcatalogueservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FoodItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String itemName;
    private String itemDescription;
    private String isVeg;
    private Number price;
    private Integer restaurantId;
    @Column(nullable = false, columnDefinition = "INT DEFAULT 0")
    private Integer quantity;
}
