package com.jdc.myfourapi.service.serviceImpl;

import com.jdc.myfourapi.entities.PortionEntity;
import com.jdc.myfourapi.repository.PortionRepository;
import com.jdc.myfourapi.service.services.IPortionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PortionServiceImpl  implements IPortionService {

    @Autowired
    private PortionRepository portionRepository;

    @Override
    public List<PortionEntity> findAll() {
        return portionRepository.findAll();
    }

    @Override
    public PortionEntity findById(Long id) {
        return portionRepository.findById(id).get();
    }

    @Override
    public void save(PortionEntity cocinerosEntity) {
        portionRepository.save(cocinerosEntity);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        portionRepository.deleteById(id);
    }
}
