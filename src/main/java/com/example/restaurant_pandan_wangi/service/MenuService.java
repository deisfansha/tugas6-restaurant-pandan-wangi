package com.example.restaurant_pandan_wangi.service;

import com.example.restaurant_pandan_wangi.model.Menu;
import com.example.restaurant_pandan_wangi.repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MenuService {
    @Autowired
    MenuRepository menuRepository;
    private Menu current;   // Untuk mengambil data terbaru saat transaksi
    private String message;

    public Menu getCurrent() {
        return current;
    }

    public String getMessage() {
        return message;
    }

    public boolean add(Menu menu) {
        if (menu.getName() != null && isNameValid(menu.getName()) &&
                menu.getPrice() > 0) {
            menuRepository.save(menu);
            message = "Menu added successfully.";
            return true;
        } else {
            message = "Input invalid.";
            return false;
        }
    }

    private boolean isNameValid(String name) {
        return name.matches("[a-zA-Z0-9\\s]+");
    }
}
