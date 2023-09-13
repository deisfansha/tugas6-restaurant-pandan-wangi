package com.example.restaurant_pandan_wangi.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class TableNumber {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private boolean isAvailable = true; // true -> meja sedang tersedia, false -> meja sedang tidak tersedia.
    private boolean isActive = true;    // Status meja : true -> meja ada, false -> meja tidak ada

    public TableNumber() {
        // Do Nothing
    }

    public long getId() {
        return id;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        this.isAvailable = available;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
