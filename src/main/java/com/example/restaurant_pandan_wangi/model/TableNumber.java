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
    private boolean isTableInUse = false; // true -> sedang dipakai, false -> sedang tersedia.
    private boolean isActive = true;     // Status meja : true -> meja ada, false -> meja tidak ada

    public TableNumber() {
        // Do Nothing
    }

    public long getId() {
        return id;
    }

    public boolean isTableInUse() {
        return isTableInUse;
    }

    public void setTableInUse(boolean tableInUse) {
        this.isTableInUse = tableInUse;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
