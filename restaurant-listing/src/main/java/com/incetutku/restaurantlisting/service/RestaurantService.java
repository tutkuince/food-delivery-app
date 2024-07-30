package com.incetutku.restaurantlisting.service;

import com.incetutku.restaurantlisting.dto.RestaurantDTO;
import com.incetutku.restaurantlisting.entity.Restaurant;
import com.incetutku.restaurantlisting.mapper.RestaurantMapper;
import com.incetutku.restaurantlisting.repository.RestaurantRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;

    public RestaurantService(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    public List<RestaurantDTO> findAllRestaurants() {
        List<Restaurant> restaurantList = restaurantRepository.findAll();
        return restaurantList.stream()
                .map(RestaurantMapper.INSTANCE::mapRestaurantToRestaurantDTO).collect(Collectors.toList());
    }

    public RestaurantDTO createRestaurant(RestaurantDTO restaurantDTO) {
        Restaurant savedRestaurant = restaurantRepository.save(RestaurantMapper.INSTANCE.mapRestaurantDTOToRestaurant(restaurantDTO));
        return RestaurantMapper.INSTANCE.mapRestaurantToRestaurantDTO(savedRestaurant);
    }

    public RestaurantDTO findRestaurantById(int id) {
        Optional<Restaurant> optionalRestaurant = restaurantRepository.findById(id);
        return optionalRestaurant.map(RestaurantMapper.INSTANCE::mapRestaurantToRestaurantDTO).orElse(null);
    }
}
