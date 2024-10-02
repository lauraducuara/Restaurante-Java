package com.jdc.myfourapi.service.services;

import com.jdc.myfourapi.entities.WineEntity;

import java.util.List;

public interface IWineService {
    public List<WineEntity> findAll();
    public WineEntity findById(Long id);
    public void save(WineEntity wineEntity);

    public void deleteById(Long id);
}
