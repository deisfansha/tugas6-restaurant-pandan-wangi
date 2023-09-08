package com.example.restaurant_pandan_wangi.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String phone_number;
    private boolean isMember = false;

    public Customer() {
        // Do Nothing
    }

    public Customer(String name, String phone_number, boolean isMember) {
        this.name = name;
        this.phone_number = phone_number;
        this.isMember = isMember;
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

    public String getPhone() {
        return phone_number;
    }

    public void setPhone(String phone_number) {
        this.phone_number = phone_number.trim();
    }

    public boolean isMember() {
        return isMember;
    }

    public void setMember(boolean is_member) {
        isMember = is_member;
    }
}
