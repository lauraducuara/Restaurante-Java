package com.jdc.myfourapi.service.services;

import com.jdc.myfourapi.entities.MenuEntity;
import com.jdc.myfourapi.entities.MenuPortionEntity;

import java.util.List;

public interface IMenuService {
    public List<MenuEntity> findAll();
    public MenuEntity findById(Long id);
    public void save(MenuEntity menuEntity);

    public void deleteById(Long id);
}
