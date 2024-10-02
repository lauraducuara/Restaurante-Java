package com.jdc.myfourapi.service.serviceImpl;

import com.jdc.myfourapi.entities.IngredientEntity;
import com.jdc.myfourapi.repository.IngredientRepository;
import com.jdc.myfourapi.service.services.IIngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class IngredientServiceImpl implements IIngredientService {

    @Autowired
    private IngredientRepository ingredientRepository;

    @Override
    public List<IngredientEntity> findAll() {
        return ingredientRepository.findAll();
    }

    @Override
    public IngredientEntity findById(Long id) {
        return ingredientRepository.findById(id).get();
    }

    @Override
    public void save(IngredientEntity cocinerosEntity) {
        ingredientRepository.save(cocinerosEntity);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        ingredientRepository.deleteById(id);
    }
}

