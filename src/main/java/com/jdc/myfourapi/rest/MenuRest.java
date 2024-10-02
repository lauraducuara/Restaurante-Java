package com.jdc.myfourapi.rest;

import com.jdc.myfourapi.entities.MenuEntity;
import com.jdc.myfourapi.service.services.IMenuService;
import com.jdc.myfourapi.utilities.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.List;

@RequestMapping("/api/menu")
@RestController
public class MenuRest {

    @Autowired
    private IMenuService menuService;


    @GetMapping("/list")
    private ResponseEntity<List<MenuEntity>> list() {
        return ResponseEntity.ok(menuService.findAll());
    }

    @GetMapping("/list/one/{id}")
    private ResponseEntity<MenuEntity> listOne(@PathVariable("id") Long id) {
        return ResponseEntity.ok(menuService.findById(id));
    }

    @PostMapping("/add")
    private ResponseEntity<MenuEntity> save
            (@Validated @RequestBody MenuEntity menuEntity) {
        try {
            menuService.save(menuEntity);
            ResponseEntity.status(200);
            return ResponseEntity.ok(menuEntity);
        } catch (Exception e) {
            System.out.println("El error es: " + e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }


    @DeleteMapping("/delete/{id}")
    private ResponseEntity<ApiResponse> deleteMenu(@PathVariable("id") Long id) {
        try {
            MenuEntity menuEntity = menuService.findById(id);
            if (menuEntity == null) {
                return ResponseEntity.ok(new ApiResponse("El menú no existe: " + id));
            }
            menuService.deleteById(id);
            return ResponseEntity.ok(new ApiResponse("El menú fue eliminado: " + id));
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("Error: " + ex.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse("Ocurrió un error inesperado: " + e.getMessage()));
        }
    }


    @PutMapping("/update/{id}")
    private ResponseEntity<MenuEntity> edit
            (@PathVariable("id") Long id, @Validated @RequestBody MenuEntity menuEntity) {
        try {
            MenuEntity menuExisting = menuService.findById(id);
            menuExisting.setMainCode(menuEntity.getMainCode());
            menuExisting.setNameMenu(menuEntity.getNameMenu());
            menuExisting.setPriceMenu(menuEntity.getPriceMenu());
            menuService.save(menuExisting);
            ResponseEntity.status(200);
            return ResponseEntity.ok(menuExisting);
        } catch (Exception e) {
            System.out.println("El error es: " + e);
            return ResponseEntity.
                    status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
