package com.jdc.myfourapi.rest;

import com.jdc.myfourapi.dto.PortionDto;
import com.jdc.myfourapi.entities.IngredientEntity;
import com.jdc.myfourapi.entities.PortionEntity;
import com.jdc.myfourapi.entities.WineEntity;
import com.jdc.myfourapi.service.services.IIngredientService;
import com.jdc.myfourapi.service.services.IPortionService;
import com.jdc.myfourapi.service.services.IWineService;
import com.jdc.myfourapi.utilities.ApiResponse;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/portion")
public class PortionRest {
    @Autowired
    private IPortionService portionService;
    @Autowired
    private IIngredientService ingredientService;
    @Autowired
    private IWineService wineService;

    @GetMapping("/list")
    private ResponseEntity<List<PortionDto>> list() {
        List<PortionEntity> portionEntities = portionService.findAll();
        List<PortionDto> portionDtos = portionEntities.stream().map(portion -> {
            PortionDto dto = new PortionDto();
            dto.setCodPortion(portion.getCodPortion());
            dto.setNamePortion(portion.getNamePortion());
            dto.setCaloriesPortion(portion.getCaloriesPortion());
            dto.setCodWine(portion.getWineEntity() != null ? portion.getWineEntity().getCodWine() : null);
            dto.setCodIngredient(portion.getIngredientEntity() != null ? portion.getIngredientEntity().getCodIngredient() : null);
            return dto;
        }).collect(Collectors.toList());

        return ResponseEntity.ok(portionDtos);
    }


    @GetMapping("/list/one/{id}")
    private ResponseEntity<PortionEntity> listOne(@PathVariable("id") Long id) {
        return ResponseEntity.ok(portionService.findById(id));
    }

    @PostMapping("/add")
    private ResponseEntity<PortionEntity> save(@Validated @RequestBody PortionDto portionDto) {
        try {
            PortionEntity portion = new PortionEntity();
            portion.setNamePortion(portionDto.getNamePortion());
            portion.setCaloriesPortion(portionDto.getCaloriesPortion());
            IngredientEntity ingredient = ingredientService.findById(portionDto.getCodIngredient());
            if (ingredient == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }

            WineEntity wine = wineService.findById(portionDto.getCodWine());
            if (wine == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }
            portion.setIngredientEntity(ingredient);
            portion.setWineEntity(wine);
            portionService.save(portion);

            return ResponseEntity.status(HttpStatus.CREATED).body(portion);
        } catch (Exception e) {
            System.out.println("El error es: " + e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }



    @DeleteMapping("/delete/{id}")
    private ResponseEntity<ApiResponse> delete(@PathVariable("id") Long id) {
        try {
            PortionEntity portionEntity = portionService.findById(id);
            if (portionEntity == null) {
                return ResponseEntity.ok(new ApiResponse("La porcion no existe: " + id));
            }
            portionService.deleteById(id);
            return ResponseEntity.ok(new ApiResponse("La porcion fue eliminada: " + id));
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse("Error: " + ex.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse("Ocurrió un error inesperado: " + e.getMessage()));
        }
    }

    @PutMapping("/update/{id}")
    private ResponseEntity<PortionEntity> edit
            (@PathVariable("id") Long id, @Validated @RequestBody PortionDto portionDto){
        try{
            //Crear un nuevo entity basado en los datos del DTO
            PortionEntity portionExistente = portionService.findById(id);
            portionExistente.setNamePortion(portionDto.getNamePortion());
            portionExistente.setCaloriesPortion(portionDto.getCaloriesPortion());

            IngredientEntity ingredient =  ingredientService.findById(portionDto.getCodIngredient());
            WineEntity wine = wineService.findById(portionDto.getCodWine());
            portionExistente.setIngredientEntity(ingredient);
            portionExistente.setWineEntity(wine);
            portionService.save(portionExistente);
            ResponseEntity.status(200);
            return ResponseEntity.ok(portionExistente);
        }catch (Exception e){
            System.out.println("El error es: " + e);
            return ResponseEntity.
                    status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
