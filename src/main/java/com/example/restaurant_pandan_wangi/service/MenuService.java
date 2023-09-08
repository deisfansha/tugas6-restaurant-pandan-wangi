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
    private MenuRepository menuRepository;
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

    public boolean updateData(long id, Menu menuRequest) {
        Optional<Menu> menuOptional = menuRepository.findById(id);

        if (!menuOptional.isPresent()) {
            message = "Menu ID Not Found.";
            return false;
        } else if (menuRequest.getName() == null ||
                !isNameValid(menuRequest.getName()) ||
                menuRequest.getPrice() <= 0) {
            message = "Input invalid.";
            return false;
        } else {
            menuOptional.get().setName(menuRequest.getName());
            menuOptional.get().setPrice(menuRequest.getPrice());
            menuOptional.get().setCategory(menuRequest.isCategory());
            menuRepository.save(menuOptional.get());
            message = "Menu with ID `" + id + "` updated successfully.";
            current = menuOptional.get();
            return true;
        }
    }

    public boolean updateStatus(long id, Menu menuRequest) {
        Optional<Menu> menuOptional = menuRepository.findById(id);

        if (!menuOptional.isPresent()) {
            message = "Menu ID Not Found.";
            return false;
        } else {
            menuOptional.get().setActive(menuRequest.isActive());
            menuRepository.save(menuOptional.get());
            if (menuRequest.isActive()) {
                message = "Menu ID `" + id + "` is now active.";
            } else {
                message = "Menu ID `" + id + "` is now inactive.";
            }
            current = menuOptional.get();
            return true;
        }
    }

    public List<Menu> getAllMenu() {
        return menuRepository.findAllMenu();
    }

    public List<Menu> getAllMenuByActive(boolean isActive) {
        return menuRepository.findMenuByActive(isActive);
    }

    public List<Menu> getAllMenuByName(String name) {
        return menuRepository.findMenuByName(name);
    }

    public List<Menu> getAllMenuByCategory(boolean category) {
        return menuRepository.findMenuByCategory(category);
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
