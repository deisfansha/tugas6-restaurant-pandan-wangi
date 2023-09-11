package com.example.restaurant_pandan_wangi.controller;

import com.example.restaurant_pandan_wangi.model.ApiResponse;
import com.example.restaurant_pandan_wangi.model.Menu;
import com.example.restaurant_pandan_wangi.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/menu")
public class MenuController {
    @Autowired
    private MenuService menuService;

    // API untuk menampilkan semua daftar Menu.
    @GetMapping("")
    public ResponseEntity getAllMenu() {
        return ResponseEntity.status(HttpStatus.OK).body(
                new ApiResponse(
                        "Success",
                        menuService.getAllMenu()
                ));
    }

    // API untuk menampilkan daftar Menu berdasarkan status aktif.
    @GetMapping("/actived/{isActive}")
    public ResponseEntity getAllMenuByActive(@PathVariable boolean isActive) {
        return ResponseEntity.status(HttpStatus.OK).body(
                new ApiResponse(
                        "Success",
                        menuService.getAllMenuByActive(isActive)
                ));
    }

    // API untuk menampilkan daftar Menu berdasarkan nama.
    @GetMapping("/named/{name}")
    public ResponseEntity getAllMenuByName(@PathVariable String name) {
        return ResponseEntity.status(HttpStatus.OK).body(
                new ApiResponse(
                        "Success",
                        menuService.getAllMenuByName(name)
                ));
    }

    // API untuk menampilkan daftar Menu berdasarkan kategori.
    @GetMapping("/category/{category}")
    public ResponseEntity getAllMenuByCategory(@PathVariable boolean category) {
        return ResponseEntity.status(HttpStatus.OK).body(
                new ApiResponse(
                        "Success",
                        menuService.getAllMenuByCategory(category)
                ));
    }

    // API untuk menampilkan informasi Menu berdasarkan ID Menu.
    @GetMapping("/{id}")
    public ResponseEntity getMenuById(@PathVariable long id) {
        if (menuService.getMenuById(id) != null) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ApiResponse(
                            "Success",
                            menuService.getMenuById(id)
                    ));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse(
                            menuService.getMessage()
                    ));
        }
    }

    // API untuk menambahkan Menu baru berdasarkan request.
    @PostMapping("")
    public ResponseEntity addMenu(@RequestBody Menu menuRequest) {
        if (menuService.add(menuRequest)) {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new ApiResponse(
                            "Success",
                            menuRequest
                    ));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse(
                            menuService.getMessage()
                    ));
        }
    }

    // API untuk memperbarui informasi Menu berdasarkan ID Menu dan request.
    @PutMapping("/{id}")
    public ResponseEntity updateDate(@PathVariable long id, @RequestBody Menu menuRequest) {
        if (menuService.updateData(id,menuRequest)) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ApiResponse(
                            "Success",
                            menuService.getCurrent()
                    ));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse(
                            menuService.getMessage()
                    ));
        }
    }

    // API untuk memperbarui status aktif Menu berdasarkan ID Menu dan request.
    @PatchMapping("/{id}/activated")
    public ResponseEntity updateActivated(@PathVariable long id, @RequestBody Menu menuRequest) {
        if (menuService.updateStatus(id,menuRequest.isActive())) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ApiResponse(
                            "Success",
                            menuService.getCurrent()
                    ));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse(
                            menuService.getMessage()
                    ));
        }
    }
}
