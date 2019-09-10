package me.kr.devKim.eatgo.interfaces;

import me.kr.devKim.eatgo.application.RestaurantService;
import me.kr.devKim.eatgo.domain.Restaurant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
public class RestaurantController {

    @Autowired
    RestaurantService restaurantService;

    private List<Restaurant> restaurants = new ArrayList<>();

    public RestaurantController() {
        restaurants.add(Restaurant.builder()
                .id(1004L)
                .name("Seoul Food")
                .address("Seoul")
                .build());

        restaurants.add(Restaurant.builder()
                .id(2020L)
                .name("Jeju Food")
                .address("Jeju")
                .build());
    }

    @GetMapping("/restaurants")
    public List<Restaurant> list() {

        List<Restaurant> restaurants = restaurantService.getRestaurants();

        return restaurants;
    }

    @GetMapping("/restaurants/{id}")
    public Restaurant detail(@PathVariable("id") Long id) {

        Restaurant restaurant = restaurantService.getRestaurant(id);

        return restaurant;
    }

    @PostMapping("/restaurants")
    public ResponseEntity<?> create(@RequestBody Restaurant resource) throws URISyntaxException {

        Restaurant restaurant = Restaurant.builder()
                .name(resource.getName())
                .address(resource.getAddress())
                .build();
        restaurantService.addRestaurant(restaurant);

        URI location = new URI("/restaurants/" + restaurant.getId());
        return ResponseEntity.created(location).body("{}");
    }

    @PatchMapping("/restaurants/{id}")
    public String update(@PathVariable("id") Long id, @RequestBody Restaurant resource) {

        String name = resource.getName();
        String address = resource.getAddress();
        restaurantService.updateRestaurant(id, name, address);

        return "{}";
    }

}
