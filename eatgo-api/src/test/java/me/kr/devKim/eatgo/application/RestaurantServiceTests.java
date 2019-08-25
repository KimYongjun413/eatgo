package me.kr.devKim.eatgo.application;

import me.kr.devKim.eatgo.domain.*;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class RestaurantServiceTests {

    private RestaurantService restaurantService;

    @Before
    public void setUp() {
        RestaurantRepository restaurantRepository = new RestaurantRepositoryImpl();
        MenuItemRepository menuItemRepository = new MenuItemRepositoryImpl();
        restaurantService = new RestaurantService(restaurantRepository, menuItemRepository);

    }

    @Test
    public void getRestaurants() {
        List<Restaurant> restaurants = restaurantService.getRestaurants();

        assertThat(restaurants.get(0).getId(), is(1004L));
    }

    @Test
    public void getRestaurant() {
        Restaurant restaurant = restaurantService.getRestaurant(1004L);

        assertThat(restaurant.getId(), is(1004L));
    }

}