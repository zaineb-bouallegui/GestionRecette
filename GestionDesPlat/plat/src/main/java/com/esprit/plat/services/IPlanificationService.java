package com.esprit.plat.services;

import com.esprit.plat.entities.Planification;
import org.springframework.http.ResponseEntity;

import java.util.Date;
import java.util.List;

public interface IPlanificationService {
    String createPlanification(Planification planification);
    Planification getPlanificationById(Long id);
    List<Planification> getAllPlanifications();
    Planification updatePlanification(Long id, Planification planification);
    String deletePlanification(Long id);
}
