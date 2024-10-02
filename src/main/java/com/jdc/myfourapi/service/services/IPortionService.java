package com.jdc.myfourapi.service.services;

import com.jdc.myfourapi.entities.PortionEntity;

import java.util.List;

public interface IPortionService {
    public List<PortionEntity> findAll();
    public PortionEntity findById(Long id);
    public void save(PortionEntity portionEntity);

    public void deleteById(Long id);
}
