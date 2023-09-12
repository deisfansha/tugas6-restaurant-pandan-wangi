package com.example.restaurant_pandan_wangi.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private int price;
    private boolean isFood;           // Kategori menu : true -> makanan, false -> minuman
    private boolean isActive = true;    // Status menu tersedia

    public Menu() {
        // Do Nothing
    }

    public Menu(String name, int price, boolean isFood) {
        this.name = name;
        this.price = price;
        this.isFood = isFood;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name.trim();
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean isFood() {
        return isFood;
    }

    public String getCategory() {
        if (isFood) return "Makanan";
        else return "Minuman";
    }

    public void setFood(boolean food) {
        isFood = food;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
