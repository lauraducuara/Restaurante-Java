package com.jdc.myfourapi.repository;

import com.jdc.myfourapi.entities.MenuEntity;
import com.jdc.myfourapi.entities.PortionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface PortionRepository  extends JpaRepository<PortionEntity, Long> {
    @Modifying
    @Query("DELETE FROM PortionEntity pe WHERE pe.codPortion = ?1")
    public void deletePortionById(Long id);
}
