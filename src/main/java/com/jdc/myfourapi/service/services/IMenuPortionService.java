package com.jdc.myfourapi.service.services;

import com.jdc.myfourapi.entities.MenuPortionEntity;

import java.util.List;

public interface IMenuPortionService {
    public List<MenuPortionEntity> findAll();
    public MenuPortionEntity findById(Long id, Long idDos);
    public void save(MenuPortionEntity menuPortionEntity);

    public void deleteById(Long id, Long idDos);
}
