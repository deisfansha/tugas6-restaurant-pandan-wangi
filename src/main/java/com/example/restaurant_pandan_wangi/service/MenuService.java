package com.example.restaurant_pandan_wangi.service;

import com.example.restaurant_pandan_wangi.model.Menu;
import com.example.restaurant_pandan_wangi.repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public boolean add(Menu menuRequest) {
        if (menuRequest.getName() != null && isNameValid(menuRequest.getName()) &&
                menuRequest.getPrice() > 0) {
            menuRepository.save(menuRequest);
            message = "Menu added successfully.";
            return true;
        } else {
            message = "Input invalid.";
            return false;
        }
    }

    public List<Menu> getAllMenu() {
        return menuRepository.findAll();
    }

    public List<Menu> getAllMenuActived() {
        return menuRepository.findAllActived();
    }

    public Menu getMenuById(long id) {
        Optional<Menu> menuOptional = menuRepository.findById(id);

        if (menuOptional.isPresent()) {
            message = "Menu ID Found.";
            return menuOptional.get();
        } else {
            message = "Menu ID Not Found.";
            return null;
        }
    }

    private boolean isNameValid(String name) {
        return name.matches("[a-zA-Z0-9\\s]+");
    }
}
