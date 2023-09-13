package com.example.restaurant_pandan_wangi.service;

import com.example.restaurant_pandan_wangi.model.TableNumber;
import com.example.restaurant_pandan_wangi.repository.TableNumberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TableNumberService {
    @Autowired
    private TableNumberRepository tableNumberRepository;
    private String message;

    public String getMessage() {
        return message;
    }

    /**
     * Menambahkan Nomor Meja baru.
     */
    public TableNumber add() {
        TableNumber tableNumber = new TableNumber();
        tableNumberRepository.save(tableNumber);
        return tableNumber;
    }

    /**
     * Memperbarui status meja sedang digunakan oleh Customer atau tidak.
     *
     * @param id        ID meja yang ingin diperbarui.
     * @param isUsed    Status meja.
     * @return          True jika berhasil diperbarui, false jika gagal.
     */
    public boolean updateInUse(long id, boolean isUsed) {
        Optional<TableNumber> tableNumberOptional = tableNumberRepository.findById(id);

        if (!tableNumberOptional.isPresent()) {
            message = "Table Not Found";
            return false;
        } else {
            tableNumberOptional.get().setAvailable(isUsed);
            tableNumberRepository.save(tableNumberOptional.get());
            return true;
        }
    }

    /**
     * Memperbarui status meja sedang tersedia dalam ruangan atau tidak.
     *
     * @param id        ID meja yang ingin diperbarui.
     * @param isActive  Status meja.
     * @return          True jika berhasil diperbarui, false jika gagal.
     */
    public boolean updateStatus(long id, boolean isActive) {
        Optional<TableNumber> tableNumberOptional = tableNumberRepository.findById(id);

        if (!tableNumberOptional.isPresent()) {
            message = "Table Not Found";
            return false;
        } else {
            tableNumberOptional.get().setActive(isActive);
            if (!isActive) {
                tableNumberOptional.get().setAvailable(false);
            }
            tableNumberRepository.save(tableNumberOptional.get());
            return true;
        }
    }

    /**
     * Mengembalikan semua daftar meja.
     *
     * @return          Daftar meja.
     */
    public List<TableNumber> getAllTable() {
        if (tableNumberRepository.count() == 0) seed();
        return tableNumberRepository.findAllByOrderById();
    }

    /**
     * Mengembalikan daftar meja berdasarkan status digunakan.
     *
     * @param isUsed    Status meja.
     * @return          Daftar meja.
     */
    public List<TableNumber> getAllTableByInUse(boolean isUsed) {
        return tableNumberRepository.findAllByIsAvailableOrderById(isUsed);
    }

    /**
     * Mengembalikan daftar meja berdasarkan status tersedia.
     *
     * @param isActive  Status meja.
     * @return          Daftar meja.
     */
    public List<TableNumber> getAllTableByActive(boolean isActive) {
        return tableNumberRepository.findAllByIsActiveOrderById(isActive);
    }

    /**
     * Mengembalikan informasi meja berdasarkan ID meja.
     *
     * @param id    ID Meja.
     * @return      Informasi meja jika tersedia, null jika tidak tersedia.
     */
    public TableNumber getTableById(long id) {
        Optional<TableNumber> tableNumberOptional = tableNumberRepository.findById(id);

        if (tableNumberOptional.isPresent()) {
            return tableNumberOptional.get();
        } else {
            message = "Table Not Found";
            return null;
        }
    }

    private void seed() {
        tableNumberRepository.save(new TableNumber());
        tableNumberRepository.save(new TableNumber());
        tableNumberRepository.save(new TableNumber());
        tableNumberRepository.save(new TableNumber());
        tableNumberRepository.save(new TableNumber());
    }
}
