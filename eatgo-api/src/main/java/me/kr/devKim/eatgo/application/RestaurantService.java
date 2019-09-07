    package me.kr.devKim.eatgo.application;

    import me.kr.devKim.eatgo.domain.MenuItem;
    import me.kr.devKim.eatgo.domain.MenuItemRepository;
    import me.kr.devKim.eatgo.domain.Restaurant;
    import me.kr.devKim.eatgo.domain.RestaurantRepository;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Service;

    import java.util.List;

@Service
public class RestaurantService {

    @Autowired
    RestaurantRepository restaurantRepository;

    @Autowired
    MenuItemRepository menuItemRepository;

    public RestaurantService(RestaurantRepository restaurantRepository, MenuItemRepository menuItemRepository) {
        this.restaurantRepository = restaurantRepository;
        this.menuItemRepository = menuItemRepository;
    }

    public Restaurant getRestaurant(Long id) {
        Restaurant restaurant = restaurantRepository.findById(id).orElse(null);
        List<MenuItem> menuItems = menuItemRepository.findByRestaurantId(id);
        restaurant.setMenuItems(menuItems);

        return restaurant;
    }

    public List<Restaurant> getRestaurants() {

        List<Restaurant> restaurants = restaurantRepository.findAll();

        return restaurants;
    }

    public Restaurant addRestaurant(Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }
}
