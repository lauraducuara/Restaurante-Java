package com.jdc.myfourapi.repository;

import com.jdc.myfourapi.entities.CoockerPortionEntity;
import com.jdc.myfourapi.entities.MenuPortionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface CoockerPortionRepository extends JpaRepository<CoockerPortionEntity, Long> {

    @Modifying
    @Query("DELETE FROM CoockerPortionEntity cp WHERE cp.codCoocker = :idCoocker AND  cp.codPortion =:idPortion")
    public void deleteCoockerPortionById(Long idCoocker, Long idPortion);


    @Query("SELECT cp FROM CoockerPortionEntity cp WHERE cp.codCoocker = :idCoocker AND  cp.codPortion =:idPortion")
    public CoockerPortionEntity selectMenuCoockerById(Long idCoocker, Long idPortion);
}

