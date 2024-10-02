package com.jdc.myfourapi.rest;

import com.jdc.myfourapi.dto.CoockerPortionDto;
import com.jdc.myfourapi.entities.CoockerEntity;
import com.jdc.myfourapi.entities.CoockerPortionEntity;
import com.jdc.myfourapi.entities.PortionEntity;
import com.jdc.myfourapi.service.services.ICoockerPortionService;
import com.jdc.myfourapi.service.services.ICoockerService;
import com.jdc.myfourapi.service.services.IPortionService;
import com.jdc.myfourapi.utilities.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.sound.sampled.Port;
import java.util.List;

@RestController
@RequestMapping("/api/coocker_portion")
public class CoockerPortionRest {

    @Autowired
    private ICoockerPortionService coockerPortionService;

    @Autowired
    private ICoockerService coockerService;

    @Autowired
    private IPortionService portionService;

    @GetMapping("/list")
    private ResponseEntity<List<CoockerPortionEntity>> list(){
        return ResponseEntity.ok(coockerPortionService.findAll());
    }
    @GetMapping("/list/one/{id}/{idDos}")
    private ResponseEntity<CoockerPortionEntity> listOne(
            @PathVariable("id") Long id,
            @PathVariable("idDos") Long idDos) {
        return ResponseEntity.ok(coockerPortionService.findById(id, idDos));
    }


    @PostMapping("/add")
    private ResponseEntity<CoockerPortionEntity> add(@Validated @RequestBody CoockerPortionDto porcionesCocineroDTO) {
        try {
            CoockerPortionEntity coockerPortionEntity = new CoockerPortionEntity();
            CoockerEntity coockerEntity = coockerService.findById(porcionesCocineroDTO.getCodCoocker());
            PortionEntity portionEntity = portionService.findById(porcionesCocineroDTO.getCodPortion());
            coockerPortionEntity.setCoockerEntity(coockerEntity);
            coockerPortionEntity.setPortionEntity(portionEntity);
            coockerPortionEntity.setCodCoocker(porcionesCocineroDTO.getCodCoocker());  // Asignar explícitamente el codcocinerosEntity
            coockerPortionEntity.setCodPortion(porcionesCocineroDTO.getCodPortion());  // Asignar explícitamente el codPorcion
            coockerPortionService.save(coockerPortionEntity);

            return ResponseEntity.ok(coockerPortionEntity);
        } catch (Exception e) {
            System.out.println("El error es: " + e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @DeleteMapping(value = "/delete/{id}/{idDos}")
    public ResponseEntity<ApiResponse> delete(@PathVariable("id") Long id, @PathVariable("idDos") Long idDos) {
        try {
            CoockerPortionEntity aux = coockerPortionService.findById(id, idDos);
            if (aux == null) {
                return ResponseEntity.ok(new ApiResponse("El id de las porciones del cocinero no existe: " + id + " y " + idDos));
            }
            coockerPortionService.deleteById(id, idDos);
            return ResponseEntity.ok(new ApiResponse("Porciones del cocinero eliminadas con éxito"));
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("Error: " + ex.getMessage()));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse("Ocurrió un error inesperado: " + ex.getMessage()));
        }
    }


    @PutMapping("/update/{codCoocker}/{codPortion}")
    private ResponseEntity<CoockerPortionEntity> edit(
            @PathVariable("codCoocker") Long codCoocker,
            @PathVariable("codPortion") Long codPortion,
            @Validated @RequestBody CoockerPortionEntity coockerPortionEntity) {
        try {
            CoockerPortionEntity coockerPortionExistent = coockerPortionService.findById(codCoocker, codPortion);
            CoockerEntity coockerEntity = coockerService.findById(coockerPortionEntity.getCodCoocker());
            PortionEntity portionEntity = portionService.findById(coockerPortionEntity.getCodPortion());

            if (coockerEntity == null || portionEntity == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(null);
            }
            coockerPortionExistent.setCoockerEntity(coockerEntity);
            coockerPortionExistent.setPortionEntity(portionEntity);
            coockerPortionService.save(coockerPortionExistent);
            return ResponseEntity.ok(coockerPortionEntity);
        } catch (Exception e) {
            System.out.println("El error es: " + e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
