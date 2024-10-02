package com.jdc.myfourapi.service.serviceImpl;

import com.jdc.myfourapi.entities.MenuPortionEntity;
import com.jdc.myfourapi.repository.MenuPortionRepository;
import com.jdc.myfourapi.service.services.IMenuPortionService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class MenuPortionServiceImpl implements IMenuPortionService {

    @Autowired
    private MenuPortionRepository menuPortionRepository;

    @Override
    public List<MenuPortionEntity> findAll() {
        return menuPortionRepository.findAll();
    }

    @Override
    public MenuPortionEntity findById(Long idMenu, Long idPortion) {
        return menuPortionRepository.selectMenusPorcionesById(idMenu, idPortion);
    }

    @Override
    public void save(MenuPortionEntity menuPortionEntity) {
        menuPortionRepository.save(menuPortionEntity);
    }

    @Override
    @Transactional
    public void deleteById(Long idMenu, Long idPortion) {
        menuPortionRepository.deleteMenuPortionById(idMenu, idPortion);
    }
}

