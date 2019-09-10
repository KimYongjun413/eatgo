package me.kr.devKim.eatgo.interfaces;

import me.kr.devKim.eatgo.application.RestaurantService;
import me.kr.devKim.eatgo.domain.MenuItem;
import me.kr.devKim.eatgo.domain.Restaurant;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(RestaurantController.class)
public class RestaurantControllerTests {

    @Autowired
    private MockMvc mvc;

    //@SpyBean(RestaurantService.class)
    @MockBean
    private RestaurantService restaurantService;

//    @SpyBean(RestaurantRepositoryImpl.class)
//    private RestaurantRepository restaurantRepository;
//
//    @SpyBean(MenuItemRepositoryImpl.class)
//    private MenuItemRepository menuItemRepository;

    @Test
    public void list() throws Exception {
        List<Restaurant> restaurants = new ArrayList<>();
        restaurants.add(Restaurant.builder()
                .id(1004L)
                .name("Seongnam Food")
                .address("Seongnam")
                .build());

        restaurants.add(Restaurant.builder()
                .id(2020L)
                .name("Anyang Food")
                .address("Anyang")
                .build());
        given(restaurantService.getRestaurants()).willReturn(restaurants);

        mvc.perform(get("/restaurants"))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        containsString("\"id\":1004")
                ))
                .andExpect(content().string(
                        containsString("\"name\":\"Seongnam Food\"")
                ))
                .andExpect(content().string(
                        containsString("\"address\":\"Seongnam\"")
                ))
                .andExpect(content().string(
                        containsString("\"inform\":\"Seongnam Food in Seongnam\"")
                ));

        mvc.perform(get("/restaurants"))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        containsString("\"id\":2020")
                ))
                .andExpect(content().string(
                        containsString("\"name\":\"Anyang Food\"")
                ))
                .andExpect(content().string(
                        containsString("\"address\":\"Anyang\"")
                ))
                .andExpect(content().string(
                        containsString("\"inform\":\"Anyang Food in Anyang\"")
                ));
    }

    @Test
    public void detail() throws Exception {

        Restaurant restaurant1 = Restaurant.builder()
                .id(1004L)
                .name("Seoul Food")
                .address("Seoul")
                .build();
        restaurant1.setMenuItems(Arrays.asList(new MenuItem("Kimchi")));
        given(restaurantService.getRestaurant(1004L)).willReturn(restaurant1);

        Restaurant restaurant2 = Restaurant.builder()
                .id(2020L)
                .name("Jeju Food")
                .address("Jeju")
                .build();
        given(restaurantService.getRestaurant(2020L)).willReturn(restaurant2);

        mvc.perform(get("/restaurants/1004"))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        containsString("\"id\":1004")
                ))
                .andExpect(content().string(
                        containsString("\"name\":\"Seoul Food\"")
                ))
                .andExpect(content().string(
                        containsString("\"address\":\"Seoul\"")
                ))
                .andExpect(content().string(
                        containsString("\"inform\":\"Seoul Food in Seoul\"")
                ))
                .andExpect(content().string(
                        containsString("\"menuItems\":[{\"name\":\"Kimchi\"}]")
                ));

        mvc.perform(get("/restaurants/2020"))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        containsString("\"id\":2020")
                ))
                .andExpect(content().string(
                        containsString("\"name\":\"Jeju Food\"")
                ))
                .andExpect(content().string(
                        containsString("\"address\":\"Jeju\"")
                ))
                .andExpect(content().string(
                        containsString("\"inform\":\"Jeju Food in Jeju\"")
                ));
    }

    @Test
    public void create() throws Exception {

        mvc.perform(post("/restaurants")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"BeRyong\",\"address\":\"Busan\"}"))
                .andExpect(status().isCreated())
//                .andExpect(header().string("location","/restaurants/1234"))
                .andExpect(content().string("{}"));

        verify(restaurantService).addRestaurant(any());
    }

    @Test
    public void update() throws Exception {
        mvc.perform(patch("/restaurants/1004")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"Seoul Bar\", \"address\":\"Busan\"}"))
                .andExpect(status().isOk());

        verify(restaurantService).updateRestaurant(1004L, "Seoul Bar", "Busan");
    }

}