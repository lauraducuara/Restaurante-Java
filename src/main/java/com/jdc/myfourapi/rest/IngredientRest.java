package com.jdc.myfourapi.rest;

import com.jdc.myfourapi.entities.IngredientEntity;
import com.jdc.myfourapi.service.services.IIngredientService;
import com.jdc.myfourapi.utilities.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/ingredient")
public class IngredientRest {
    
    @Autowired
    private IIngredientService ingredientService;

    @GetMapping("/list")
    private ResponseEntity<List<IngredientEntity>> list() {
        return ResponseEntity.ok(ingredientService.findAll());
    }

    @GetMapping("/list/one/{id}")
    private ResponseEntity<IngredientEntity> listOne(@PathVariable("id") Long id) {
        return ResponseEntity.ok(ingredientService.findById(id));
    }

    @PostMapping("/add")
    private ResponseEntity<IngredientEntity> save
            (@Validated @RequestBody IngredientEntity ingredientEntity) {
        try {
            ingredientService.save(ingredientEntity);
            ResponseEntity.status(200);
            return ResponseEntity.ok(ingredientEntity);
        } catch (Exception e) {
            System.out.println("El error es: " + e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }


    @DeleteMapping("/delete/{id}")
    private ResponseEntity<ApiResponse> deleteIngredient(@PathVariable("id") Long id) {
        try {
            IngredientEntity ingredientEntity = ingredientService.findById(id);
            if (ingredientEntity == null) {
                return ResponseEntity.ok(new ApiResponse("El ingrediente no existe: " + id));
            }
            ingredientService.deleteById(id);
            return ResponseEntity.ok(new ApiResponse("El ingrediente fue eliminado: " + id));
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("Error: " + ex.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse("Ocurrió un error inesperado: " + e.getMessage()));
        }
    }


    @PutMapping("/update/{id}")
    private ResponseEntity<IngredientEntity> edit(
            @PathVariable("id") Long id, @Validated @RequestBody IngredientEntity ingredientEntity) {
        try {
            IngredientEntity ingredient = ingredientService.findById(id);
            if (ingredient == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
            ingredient.setNameIngredient(ingredientEntity.getNameIngredient());
            ingredient.setCaloriesIngredient(ingredientEntity.getCaloriesIngredient());
            ingredient.setPriceIngredient(ingredientEntity.getPriceIngredient());
            ingredient.setDescriptionIngredient(ingredientEntity.getDescriptionIngredient());
            ingredient.setExistenceIngredient(ingredientEntity.getExistenceIngredient());
            ingredientService.save(ingredient);
            return ResponseEntity.ok(ingredient);
        } catch (Exception e) {
            System.out.println("El error es: " + e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

}
