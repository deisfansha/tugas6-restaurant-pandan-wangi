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
    private Long id;
    @ManyToOne
    @JoinColumn(name = "id_order", referencedColumnName = "id")
    private Order order;
    @ManyToOne
    @JoinColumn(name = "id_menu", referencedColumnName = "id")
    private Menu menu;
    private Integer price;
    private Integer quantity;
    private Integer statusOrder = 0;    // 0 -> Pending, 1 -> Cooking, 2 -> Deliver, 3 -> Done
    private Boolean isDeleted = false;

    public DetailOrder() {
        // Do Nothing
    }

    public Long getId() {
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

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getStatus() {
        switch (statusOrder) {
            case 0 :
                return "Pending";
            case 1 :
                return "Cooking";
            case 2 :
                return "Deliver";
            case 3 :
                return "Done";
            default:
                return "Status not found";
        }
    }

    public Integer getStatusOrder() {
        return statusOrder;
    }

    public void setStatusOrder(Integer statusOrder) {
        this.statusOrder = statusOrder;
    }

    @JsonIgnore
    public Boolean getDeleted() {
        return isDeleted;
    }

    public void delete() {
        isDeleted = true;
    }
}
