package com.example.restaurant_pandan_wangi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    @Column(name = "phone_number")
    private String phoneNumber;
    private boolean position = true;    // Posisi karyawan : true -> staff, false -> koki
    @Column(name = "actived")
    private boolean isActive = true;

    public Employee() {
        // Do Nothing
    }

    public Employee(String name, String phoneNumber, boolean position) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.position = position;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name.trim();
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber.trim();
    }

    public String getPosition() {
        if(position){
            return "Staff";
        }else {
            return "Koki";
        }
    }

    public void setPosition(boolean position) {
        this.position = position;
    }

    public boolean isActive() {
        return isActive;
    }

    public boolean isPosition() {
        return position;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
