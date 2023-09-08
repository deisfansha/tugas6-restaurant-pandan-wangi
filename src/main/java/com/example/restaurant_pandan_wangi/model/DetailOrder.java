package com.example.restaurant_pandan_wangi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class DetailOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    @JoinColumn(name = "id_order", referencedColumnName = "id")
    private Order order;
    @ManyToOne
    @JoinColumn(name = "id_menu", referencedColumnName = "id")
    private Menu menu;
    private int price;
    private int quantity;
    private int statusOrder = 0;    // 0 -> Pending, 1 -> Dimasak, 2 -> Diantarkan, 3 -> Disajikan

    public DetailOrder() {
        // Do Nothing
    }

    public long getId() {
        return id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
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
                return "Pending";
            case 1 :
                return "Dimasak";
            case 2 :
                return "Diantarkan";
            case 3 :
                return "Disajikan";
            default:
                return "Status tidak ditemukan";
        }
    }

    @JsonIgnore
    public int getStatus() {
        return statusOrder;
    }


    public void setStatusOrder(int statusOrder) {
        this.statusOrder = statusOrder;
    }
}
