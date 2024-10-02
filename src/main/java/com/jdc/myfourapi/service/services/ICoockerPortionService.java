package com.jdc.myfourapi.service.services;

import com.jdc.myfourapi.entities.CoockerPortionEntity;

import java.util.List;

public interface ICoockerPortionService {
    public List<CoockerPortionEntity> findAll();
    public CoockerPortionEntity findById(Long id, Long idDos);
    public void save(CoockerPortionEntity coockerPortionEntity);
    public void deleteById(Long id, Long idDos);
}
