package com.jdc.myfourapi.repository;

import com.jdc.myfourapi.entities.CoockerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface CoockerRepository  extends JpaRepository<CoockerEntity, Long> {

    @Modifying
    @Query("DELETE FROM CoockerEntity co WHERE co.codCoocker = ?1")
    public void deleteCoockerById(Long id);

}
