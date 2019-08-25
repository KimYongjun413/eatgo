package me.kr.devKim.eatgo.domain;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Restaurant {

    private Long id;
    private String name;
    private String address;
    private final List<MenuItem> menuItems = new ArrayList<>();

    public Restaurant() {
    }

    public Restaurant(Long id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getaddress() {
        return address;
    }

    public String getInform() {
        return this.name + " in " + this.address;
    }

    public List<MenuItem> getMenuItems() {
        return menuItems;
    }

    public void addMenuItem(MenuItem menuItem) {
        this.menuItems.add(menuItem);
    }

    public void setMenuItems(List<MenuItem> menuItems) {
        for(MenuItem menuItem : menuItems) {
            this.menuItems.add(menuItem);
        }
    }
}
