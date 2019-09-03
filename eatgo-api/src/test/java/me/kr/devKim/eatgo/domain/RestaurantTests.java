package me.kr.devKim.eatgo.domain;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.is;

public class RestaurantTests {

    @Test
    public void creation() {
        Restaurant restaurant = new Restaurant(1004L, "Bob zip", "Seoul");
        assertThat(restaurant.getName(), is("Bob zip"));
    }

    @Test
    public void information() {
        Restaurant restaurant = new Restaurant(1004L, "Bob zip", "Seoul");
        assertThat(restaurant.getInform(), is("Bob zip in Seoul"));
    }

}