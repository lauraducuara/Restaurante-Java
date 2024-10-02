package com.jdc.myfourapi.service.serviceImpl;

import com.jdc.myfourapi.entities.WineEntity;
import com.jdc.myfourapi.repository.WineRepository;
import com.jdc.myfourapi.service.services.IWineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class WineServiceImpl  implements IWineService {

    @Autowired
    private WineRepository wineRepository;

    @Override
    public List<WineEntity> findAll() {
        return wineRepository.findAll();
    }

    @Override
    public WineEntity findById(Long id) {
        return wineRepository.findById(id).get();
    }

    @Override
    public void save(WineEntity cocinerosEntity) {
        wineRepository.save(cocinerosEntity);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        wineRepository.deleteById(id);
    }
}
