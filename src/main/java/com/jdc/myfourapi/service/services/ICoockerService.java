package com.jdc.myfourapi.service.services;

import com.jdc.myfourapi.entities.CoockerEntity;
import com.jdc.myfourapi.entities.CoockerPortionEntity;

import java.util.List;

public interface ICoockerService {
    public List<CoockerEntity> findAll();
    public CoockerEntity findById(Long id);
    public void save(CoockerEntity coockerEntity);
    public void deleteById(Long id);
}
