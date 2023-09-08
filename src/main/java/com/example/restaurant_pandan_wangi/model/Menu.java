package com.example.restaurant_pandan_wangi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
    private boolean category;       // Kategori menu, true untuk makanan, false untuk minuman
    private boolean isActive = true;       // Status menu tersedia

    public Menu() {
        // Do Nothing
    }

    public Menu(String name, int price, boolean category) {
        this.name = name;
        this.price = price;
        this.category = category;
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

    public boolean isCategory() {
        return category;
    }

    public String getCategory() {
        if (category) return "Makanan";
        else return "Minuman";
    }

    public void setCategory(boolean category) {
        this.category = category;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
