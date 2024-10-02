package com.jdc.myfourapi.repository;

import com.jdc.myfourapi.entities.MenuEntity;
import com.jdc.myfourapi.entities.MenuPortionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface MenuPortionRepository extends JpaRepository<MenuPortionEntity, Long> {

    @Modifying
    @Query("DELETE FROM MenuPortionEntity mp WHERE mp.codMenu = :idMenu AND  mp.codPortion =:idPortion")
    public void deleteMenuPortionById(Long idMenu, Long idPortion);


    @Query("SELECT me FROM MenuPortionEntity me WHERE me.codMenu = :idMenu AND  me.codPortion =:idPortion")
    public MenuPortionEntity selectMenusPorcionesById(Long idMenu, Long idPortion);
}
