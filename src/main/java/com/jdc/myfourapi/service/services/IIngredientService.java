package com.jdc.myfourapi.service.services;

import com.jdc.myfourapi.entities.IngredientEntity;

import java.util.List;

public interface IIngredientService {
    public List<IngredientEntity> findAll();
    public IngredientEntity findById(Long id);
    public void save(IngredientEntity ingredientEntity);

    public void deleteById(Long id);
}
