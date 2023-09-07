package com.example.restaurant_pandan_wangi.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class TrDetailOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long idTrOrder;
    private int price;
    private int quantity;
    private int statusOrder;    // 0 -> Sedang dimasak, 1 -> Sedang diantarkan, 2 -> Sudah disajikan

    public TrDetailOrder() {
        // Do Nothing
    }

    public long getId() {
        return id;
    }

    public long getIdTrOrder() {
        return idTrOrder;
    }

    public void setIdTrOrder(long idTrOrder) {
        this.idTrOrder = idTrOrder;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getStatusOrder() {
        switch (statusOrder) {
            case 0 :
                return "Sedang dimasak";
            case 1 :
                return "Sedang diantarkan";
            case 2 :
                return "Sudah disajikan";
            default:
                return "Status tidak ditemukan";
        }
    }

    public int getStatus() {
        return statusOrder;
    }


    public void setStatusOrder(int statusOrder) {
        this.statusOrder = statusOrder;
    }
}
