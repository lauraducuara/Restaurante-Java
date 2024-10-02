package com.jdc.myfourapi.rest;

import com.jdc.myfourapi.entities.WineEntity;
import com.jdc.myfourapi.service.services.IWineService;
import com.jdc.myfourapi.utilities.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/wine")
public class WineRest {
    @Autowired
    private IWineService wineService;

    @GetMapping("/list")
    private ResponseEntity<List<WineEntity>> list() {
        return ResponseEntity.ok(wineService.findAll());
    }

    @GetMapping("/list/one/{id}")
    private ResponseEntity<WineEntity> listOne(@PathVariable("id") Long id) {
        return ResponseEntity.ok(wineService.findById(id));
    }

    @PostMapping("/add")
    private ResponseEntity<WineEntity> save
            (@Validated @RequestBody WineEntity wineEntity) {
        try {
            wineService.save(wineEntity);
            ResponseEntity.status(200);
            return ResponseEntity.ok(wineEntity);
        } catch (Exception e) {
            System.out.println("El error es: " + e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }


    @DeleteMapping("/delete/{id}")
    private ResponseEntity<ApiResponse> delete(@PathVariable("id") Long id) {
        try {
            WineEntity wineEntity = wineService.findById(id);
            if (wineEntity == null) {
                return ResponseEntity.ok(new ApiResponse("El vino no existe: " + id));
            }
            wineService.deleteById(id);
            return ResponseEntity.ok(new ApiResponse("El vino fue eliminado: " + id));
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse("Error: " + ex.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse("Ocurrió un error inesperado: " + e.getMessage()));
        }
    }


    @PutMapping("/update/{id}")
    private ResponseEntity<WineEntity> edit(@PathVariable("id") Long id, @Validated @RequestBody WineEntity wineEntity) {
        try {
            WineEntity wine = wineService.findById(id);
            if (wine == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
            wine.setTipeWine(wineEntity.getTipeWine());
            wine.setNameWine(wineEntity.getNameWine().trim());
            wine.setDateWine(wineEntity.getDateWine());
            wineService.save(wine);
            return ResponseEntity.ok(wine);
        } catch (Exception e) {
            System.out.println("El error es: " + e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

}
