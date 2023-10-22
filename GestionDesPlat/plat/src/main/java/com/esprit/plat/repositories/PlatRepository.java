package com.esprit.plat.repositories;

import com.esprit.plat.entities.Planification;
import com.esprit.plat.entities.Plat;
import com.esprit.plat.entities.Recette;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PlatRepository extends JpaRepository<Plat, Long> {


    @Query("SELECT  p FROM Plat p LEFT JOIN FETCH p.recette WHERE p.id = :id")
    Plat findPlatWithRecette(@Param("id") Long id);



}
