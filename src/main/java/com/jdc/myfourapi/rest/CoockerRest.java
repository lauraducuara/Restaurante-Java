package com.jdc.myfourapi.rest;

import com.jdc.myfourapi.entities.CoockerEntity;
import com.jdc.myfourapi.service.services.ICoockerService;
import com.jdc.myfourapi.utilities.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/coocker")
public class CoockerRest {

    @Autowired
    private ICoockerService coockerService;

    @GetMapping("/list")
    private ResponseEntity<List<CoockerEntity>> list() {
        return ResponseEntity.ok(coockerService.findAll());
    }

    @GetMapping("/list/one/{id}")
    private ResponseEntity<CoockerEntity> listOne(@PathVariable("id") Long id) {
        return ResponseEntity.ok(coockerService.findById(id));
    }

    @PostMapping("/add")
    private ResponseEntity<CoockerEntity> save
            (@Validated @RequestBody CoockerEntity coockerEntity) {
        try {
            coockerService.save(coockerEntity);
            ResponseEntity.status(200);
            return ResponseEntity.ok(coockerEntity);
        } catch (Exception e) {
            System.out.println("El error es: " + e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }


    @DeleteMapping("/delete/{id}")
    private ResponseEntity<ApiResponse> deleteCoocker(@PathVariable("id") Long id) {
        try {
            CoockerEntity coockerEntity = coockerService.findById(id);
            if (coockerEntity == null) {
                return ResponseEntity.ok(new ApiResponse("El cocinero no existe: " + id));
            }
            coockerService.deleteById(id);
            return ResponseEntity.ok(new ApiResponse("El cocinero fue eliminado: " + id));
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("Error: " + ex.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse("Ocurrió un error inesperado: " + e.getMessage()));
        }
    }


    @PutMapping("/update/{id}")
    private ResponseEntity<CoockerEntity> edit
            (@PathVariable("id") Long id, @Validated @RequestBody CoockerEntity coockerEntity) {
        try {
            CoockerEntity coocker = coockerService.findById(id);
            coocker.setIdentificationCoocker(coockerEntity.getIdentificationCoocker());
            coocker.setNamesCoocker(coockerEntity.getNamesCoocker());
            coocker.setFirstLastName(coockerEntity.getFirstLastName());
            coocker.setSecondLastName(coockerEntity.getSecondLastName());
            coocker.setDateBornCoocker(coockerEntity.getDateBornCoocker());
            coockerService.save(coocker);
            ResponseEntity.status(200);
            return ResponseEntity.ok(coocker);
        } catch (Exception e) {
            System.out.println("El error es: " + e);
            return ResponseEntity.
                    status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
