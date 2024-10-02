package com.jdc.myfourapi.repository;

import com.jdc.myfourapi.entities.IngredientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface IngredientRepository  extends JpaRepository<IngredientEntity, Long> {
    @Modifying
    @Query("DELETE FROM IngredientEntity ie WHERE ie.codIngredient = ?1")
    public void deleteIngredientById(Long id);
}
