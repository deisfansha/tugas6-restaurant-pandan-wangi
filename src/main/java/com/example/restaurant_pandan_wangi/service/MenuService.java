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
            return true;
        } else {
            message = "Input Invalid.";
            return false;
        }
    }

    public boolean updateData(long id, Menu menuRequest) {
        Optional<Menu> menuOptional = menuRepository.findById(id);

        if (!menuOptional.isPresent()) {
            message = "Menu Not Found";
            return false;
        } else if (menuRequest.getName() == null ||
                !isNameValid(menuRequest.getName()) ||
                menuRequest.getPrice() <= 0) {
            message = "Input Invalid";
            return false;
        } else {
            menuOptional.get().setName(menuRequest.getName());
            menuOptional.get().setPrice(menuRequest.getPrice());
            menuOptional.get().setCategory(menuRequest.isCategory());
            menuRepository.save(menuOptional.get());
            current = menuOptional.get();
            return true;
        }
    }

    public boolean updateStatus(long id, Menu menuRequest) {
        Optional<Menu> menuOptional = menuRepository.findById(id);

        if (!menuOptional.isPresent()) {
            message = "Menu Not Found";
            return false;
        } else {
            menuOptional.get().setActive(menuRequest.isActive());
            menuRepository.save(menuOptional.get());
            current = menuOptional.get();
            return true;
        }
    }

    public List<Menu> getAllMenu() {
        if (menuRepository.count() == 0) seed();
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
            return menuOptional.get();
        } else {
            message = "Menu Not Found";
            return null;
        }
    }

    private boolean isNameValid(String name) {
        return name.matches("[a-zA-Z0-9\\s]+");
    }

    private void seed() {
        menuRepository.save(new Menu("Nasi Goreng", 15000, true));
        menuRepository.save(new Menu("Mie Goreng", 12000, true));
        menuRepository.save(new Menu("Ikan Goreng", 20000, true));
        menuRepository.save(new Menu("Es Teh Manis", 5000, false));
        menuRepository.save(new Menu("Es Jeruk", 8000, false));
    }
}
