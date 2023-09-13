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
    private String message;

    public String getMessage() {
        return message;
    }

    /**
     * Menambahkan Menu baru.
     *
     * @param menuRequest   Menu yang akan ditambahkan.
     * @return              True jika berhasil ditambahkan, false jika gagal.
     */
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

    /**
     * Memperbarui informasi Menu yang ada berdasarkan ID Menu.
     *
     * @param id            ID Menu yang akan diperbarui.
     * @param menuRequest   Informasi Menu yang ingin diperbarui.
     * @return              True jika berhasil diperbarui, false jika gagal.
     */
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
            menuOptional.get().setFood(menuRequest.isFood());
            menuRepository.save(menuOptional.get());
            return true;
        }
    }

    /**
     * Memperbarui status Menu yang ada berdasarkan ID Menu.
     *
     * @param id        ID Menu yang akan diperbarui.
     * @param isActive  Status aktif Menu yang ingin diperbarui.
     * @return          True jika berhasil diperbarui, false jika gagal.
     */
    public boolean updateStatus(long id, boolean isActive) {
        Optional<Menu> menuOptional = menuRepository.findById(id);

        if (!menuOptional.isPresent()) {
            message = "Menu Not Found";
            return false;
        } else {
            menuOptional.get().setActive(isActive);
            menuRepository.save(menuOptional.get());
            return true;
        }
    }

    /**
     * Mengembalikan semua daftar Menu.
     *
     * @return          Daftar Menu.
     */
    public List<Menu> getAllMenu() {
        if (menuRepository.count() == 0) seed();
        return menuRepository.findAllByOrderByNameAsc();
    }

    /**
     * Mengembalikan daftar Menu berdasarkan aktif.
     *
     * @param isActive  Status aktif pada Menu.
     * @return          Daftar Menu.
     */
    public List<Menu> getAllMenuByActive(boolean isActive) {
        return menuRepository.findAllByIsActiveOrderByNameAsc(isActive);
    }

    /**
     * Mengembalikan daftar Menu berdasarkan nama.
     *
     * @param name  Nama menu yang ingin dicari.
     * @return      Daftar Menu.
     */
    public List<Menu> getAllMenuByName(String name) {
        return menuRepository.findAllByNameContainingOrderByNameAsc(name);
    }

    /**
     * Mengembalikan daftar Menu berdasarkan kategori.
     *
     * @param category  Kategori makanan yang ingin dicari.
     * @return          Daftar Menu.
     */
    public List<Menu> getAllMenuByFood(boolean category) {
        return menuRepository.findAllByIsFoodOrderByNameAsc(category);
    }

    /**
     * Mengembalikan Menu berdasarkan ID Menu.
     *
     * @param id    ID Menu.
     * @return      Menu jika tersedia, null jika tidak tersedia.
     */
    public Menu getMenuById(long id) {
        Optional<Menu> menuOptional = menuRepository.findById(id);

        if (menuOptional.isPresent()) {
            return menuOptional.get();
        } else {
            message = "Menu Not Found";
            return null;
        }
    }

    /**
     * Memeriksa apakah sebuah nama valid.
     * Nama yang valid hanya mengandung huruf (a-z, A-Z), angka (0-9), dan spasi.
     *
     * @param name      Nama yang akan diperiksa.
     * @return true     Jika nama valid, false jika tidak valid.
     */
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
