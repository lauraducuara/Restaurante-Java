package com.jdc.myfourapi.service.serviceImpl;

import com.jdc.myfourapi.entities.CoockerPortionEntity;
import com.jdc.myfourapi.repository.CoockerPortionRepository;
import com.jdc.myfourapi.repository.CoockerRepository;
import com.jdc.myfourapi.service.services.ICoockerPortionService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoockerPortionServiceImpl implements ICoockerPortionService {

    @Autowired
    private CoockerPortionRepository coockerPortionRepository;

    @Override
    public List<CoockerPortionEntity> findAll() {
        return coockerPortionRepository.findAll();
    }

    @Override
    public CoockerPortionEntity findById(Long idCoocker, Long idPortion) {
        return coockerPortionRepository.selectMenuCoockerById(idCoocker, idPortion);
    }

    @Override
    public void save(CoockerPortionEntity coockerPortionEntity) {
        coockerPortionRepository.save(coockerPortionEntity);
    }

    @Override
    @Transactional
    public void deleteById(Long idCoocker, Long idPortion) {
        coockerPortionRepository.deleteCoockerPortionById(idCoocker, idPortion);
    }
}
