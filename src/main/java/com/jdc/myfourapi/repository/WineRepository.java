package com.jdc.myfourapi.repository;

import com.jdc.myfourapi.entities.MenuEntity;
import com.jdc.myfourapi.entities.WineEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface WineRepository  extends JpaRepository<WineEntity, Long> {
    @Modifying
    @Query("DELETE FROM WineEntity we WHERE we.codWine = ?1")
    public void deleteWineById(Long id);
}
