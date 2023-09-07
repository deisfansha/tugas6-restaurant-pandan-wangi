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
    MenuService menuService;

    @GetMapping("")
    public ResponseEntity getAllMenu() {
        return ResponseEntity.status(HttpStatus.OK).body(
                new ApiResponse(
                        "All list Menu.",
                        menuService.getAllMenu()
                ));
    }

    @GetMapping("/actived/{isActive}")
    public ResponseEntity getAllMenuByActive(@PathVariable boolean isActive) {
        return ResponseEntity.status(HttpStatus.OK).body(
                new ApiResponse(
                        (isActive) ? "All list Menu active." : "All list Menu non-active.",
                        menuService.getAllMenuByActive(isActive)
                ));
    }

    @GetMapping("/named/{name}")
    public ResponseEntity getAllMenuByName(@PathVariable String name) {
        return ResponseEntity.status(HttpStatus.OK).body(
                new ApiResponse(
                        "All list Menu.",
                        menuService.getAllMenuByName(name)
                ));
    }

    @GetMapping("/category/{category}")
    public ResponseEntity getAllMenuByCategory(@PathVariable boolean category) {
        return ResponseEntity.status(HttpStatus.OK).body(
                new ApiResponse(
                        (category) ? "All list Menu by Food." : "All list Menu by Drink.",
                        menuService.getAllMenuByCategory(category)
                ));
    }

    @GetMapping("/{id}")
    public ResponseEntity getMenuById(@PathVariable long id) {
        if (menuService.getMenuById(id) != null) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ApiResponse(
                            menuService.getMessage(),
                            menuService.getMenuById(id)
                    ));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse(
                            menuService.getMessage()
                    ));
        }
    }

    @PostMapping("")
    public ResponseEntity addCourse(@RequestBody Menu menuRequest) {
        if (menuService.add(menuRequest)) {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new ApiResponse(
                            menuService.getMessage(),
                            menuRequest
                    ));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse(
                            menuService.getMessage()
                    ));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity updateCourse(@PathVariable long id, @RequestBody Menu menuRequest) {
        if (menuService.updateData(id,menuRequest)) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ApiResponse(
                            menuService.getMessage(),
                            menuService.getCurrent()
                    ));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse(
                            menuService.getMessage()
                    ));
        }
    }

    @PatchMapping("/{id}/activated")
    public ResponseEntity updateActivated(@PathVariable long id, @RequestBody Menu menuRequest) {
        if (menuService.updateStatus(id,menuRequest)) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ApiResponse(
                            menuService.getMessage(),
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
