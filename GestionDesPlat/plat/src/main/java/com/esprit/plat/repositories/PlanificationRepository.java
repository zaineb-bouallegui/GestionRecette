package com.esprit.plat.repositories;

import com.esprit.plat.entities.Planification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlanificationRepository extends JpaRepository<Planification, Long> {

    // Add a custom query to find Planifications by userId
    @Query("SELECT p FROM Planification p WHERE p.userId = :userId")
    List<Planification> findByUserId(@Param("userId") Long userId);
}

