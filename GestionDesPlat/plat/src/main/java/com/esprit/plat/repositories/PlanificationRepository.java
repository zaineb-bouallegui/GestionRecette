package com.esprit.plat.repositories;

import com.esprit.plat.entities.Planification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlanificationRepository extends JpaRepository<Planification, Long> {
}
