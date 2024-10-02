package com.jdc.myfourapi.service.serviceImpl;

import com.jdc.myfourapi.entities.MenuEntity;
import com.jdc.myfourapi.repository.MenuRepository;
import com.jdc.myfourapi.service.services.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MenuServiceImpl  implements IMenuService {

    @Autowired
    private MenuRepository menuRepository;

    @Override
    public List<MenuEntity> findAll() {
        return menuRepository.findAll();
    }

    @Override
    public MenuEntity findById(Long id) {
        return menuRepository.findById(id).get();
    }

    @Override
    public void save(MenuEntity cocinerosEntity) {
        menuRepository.save(cocinerosEntity);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        menuRepository.deleteById(id);
    }
}

