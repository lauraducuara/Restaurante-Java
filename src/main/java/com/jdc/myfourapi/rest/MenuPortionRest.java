package com.jdc.myfourapi.rest;

import com.jdc.myfourapi.dto.CoockerPortionDto;
import com.jdc.myfourapi.dto.MenuPortionDto;
import com.jdc.myfourapi.entities.MenuEntity;
import com.jdc.myfourapi.entities.MenuPortionEntity;
import com.jdc.myfourapi.entities.MenuPortionEntity;
import com.jdc.myfourapi.entities.PortionEntity;
import com.jdc.myfourapi.service.services.IMenuService;
import com.jdc.myfourapi.service.services.IMenuPortionService;
import com.jdc.myfourapi.service.services.IPortionService;
import com.jdc.myfourapi.utilities.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/menu_portion")
public class MenuPortionRest {

    @Autowired
    private IMenuPortionService menuPortionService;

    @Autowired
    private IMenuService menuService;

    @Autowired
    private IPortionService portionService;

    @GetMapping("/list")
    private ResponseEntity<List<MenuPortionEntity>> list(){
        return ResponseEntity.ok(menuPortionService.findAll());
    }
    @GetMapping("/list/one/{id}/{idDos}")
    private ResponseEntity<MenuPortionEntity> listOne(
            @PathVariable("id") Long id,
            @PathVariable("idDos") Long idDos) {
        return ResponseEntity.ok(menuPortionService.findById(id, idDos));
    }


    @PostMapping("/add")
    private ResponseEntity<MenuPortionEntity> add(@Validated @RequestBody MenuPortionDto menuPortionDto) {
        try {
            MenuPortionEntity MenuPortionEntity = new MenuPortionEntity();
            MenuEntity menuEntity = menuService.findById(menuPortionDto.getCodMenu());
            PortionEntity portionEntity = portionService.findById(menuPortionDto.getCodPortion());
            MenuPortionEntity.setMenuEntity(menuEntity);
            MenuPortionEntity.setPortionEntity(portionEntity);
            MenuPortionEntity.setCodMenu(menuPortionDto.getCodMenu());
            MenuPortionEntity.setCodPortion(menuPortionDto.getCodPortion());
            menuPortionService.save(MenuPortionEntity);

            return ResponseEntity.ok(MenuPortionEntity);
        } catch (Exception e) {
            System.out.println("El error es: " + e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @DeleteMapping(value = "/delete/{id}/{idDos}")
    public ResponseEntity<ApiResponse> delete(@PathVariable("id") Long id, @PathVariable("idDos") Long idDos) {
        try {
            MenuPortionEntity aux = menuPortionService.findById(id, idDos);
            if (aux == null) {
                return ResponseEntity.ok(new ApiResponse("El id de las porciones del menú no existe: " + id + " y " + idDos));
            }
            menuPortionService.deleteById(id, idDos);
            return ResponseEntity.ok(new ApiResponse("Porciones del menú eliminadas con éxito"));
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("Error: " + ex.getMessage()));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse("Ocurrió un error inesperado: " + ex.getMessage()));
        }
    }


    @PutMapping("/update/{codMenu}/{codPortion}")
    private ResponseEntity<MenuPortionEntity> edit(
            @PathVariable("codMenu") Long codMenu,
            @PathVariable("codPortion") Long codPortion,
            @Validated @RequestBody MenuPortionEntity MenuPortionEntity) {
        try {
            MenuPortionEntity menuPortionEntity = menuPortionService.findById(codMenu, codPortion);
            MenuEntity menuEntity = menuService.findById(MenuPortionEntity.getCodMenu());
            PortionEntity portionEntity = portionService.findById(MenuPortionEntity.getCodPortion());

            if (menuEntity == null || portionEntity == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(null);
            }
            menuPortionEntity.setMenuEntity(menuEntity);
            menuPortionEntity.setPortionEntity(portionEntity);
            menuPortionService.save(menuPortionEntity);
            return ResponseEntity.ok(MenuPortionEntity);
        } catch (Exception e) {
            System.out.println("El error es: " + e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
