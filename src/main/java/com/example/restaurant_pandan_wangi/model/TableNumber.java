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
    private boolean isTableInUse = false; // Status meja sedang ditempati atau tidak, true untuk ditempati dan false untuk belum ditempati.
    private boolean isActive = true;     // Status meja sedang digunakan atau tidak

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
