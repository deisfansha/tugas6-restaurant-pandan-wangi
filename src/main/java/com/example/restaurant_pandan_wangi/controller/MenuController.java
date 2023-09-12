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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/menus")
public class MenuController {
    @Autowired
    private MenuService menuService;

    // API untuk menampilkan daftar Menu berdasarkan request.
    @GetMapping("")
    public ResponseEntity getAllMenuBy(
            @RequestParam(required = false) String name,
            @RequestParam(name = "is_active", required = false) Boolean isActive,
            @RequestParam(name = "is_chief", required = false) Boolean isChief) {
        if (name != null) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ApiResponse(
                            "Success",
                            menuService.getAllMenuByName(name)
                    ));
        } else if (isChief != null) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ApiResponse(
                            "Success",
                            menuService.getAllMenuByFood(isChief)
                    ));
        } else if (isActive != null) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ApiResponse(
                            "Success",
                            menuService.getAllMenuByActive(isActive)
                    ));
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ApiResponse(
                            "Success",
                            menuService.getAllMenu()
                    ));
        }
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
                            menuService.getMenuById(id)
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
                            menuService.getMenuById(id)
                    ));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse(
                            menuService.getMessage()
                    ));
        }
    }
}
