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
        restaurants.add(new Restaurant(1004L, "Seoul Food", "Seoul"));
        restaurants.add(new Restaurant(2020L, "Jeju Food", "Jeju"));
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
        String name = resource.getName();
        String address = resource.getaddress();

        Restaurant restaurant = new Restaurant(name, address);
        restaurantService.addRestaurant(restaurant);

        URI location = new URI("/restaurants/" + restaurant.getId());
        return ResponseEntity.created(location).body("{}");
    }

}
