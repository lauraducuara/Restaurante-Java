package com.jdc.myfourapi.repository;

import com.jdc.myfourapi.entities.MenuEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface MenuRepository  extends JpaRepository<MenuEntity, Long> {
    @Modifying
    @Query("DELETE FROM MenuEntity me WHERE me.codMenu = ?1")
    public void deleteMenuById(Long id);

}
