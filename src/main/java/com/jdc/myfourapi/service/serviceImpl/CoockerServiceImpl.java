package com.jdc.myfourapi.service.serviceImpl;

import com.jdc.myfourapi.entities.CoockerEntity;
import com.jdc.myfourapi.repository.CoockerRepository;
import com.jdc.myfourapi.service.services.ICoockerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CoockerServiceImpl  implements ICoockerService {

    @Autowired
    private CoockerRepository coockerRepository;

    @Override
    public List<CoockerEntity> findAll() {
        return coockerRepository.findAll();
    }

    @Override
    public CoockerEntity findById(Long id) {
        return coockerRepository.findById(id).get();
    }

    @Override
    public void save(CoockerEntity cocinerosEntity) {
        coockerRepository.save(cocinerosEntity);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        coockerRepository.deleteById(id);
    }
}
