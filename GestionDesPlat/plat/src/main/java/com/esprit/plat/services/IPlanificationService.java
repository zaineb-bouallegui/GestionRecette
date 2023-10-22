package com.esprit.plat.services;

import com.esprit.plat.entities.Planification;
import org.springframework.http.ResponseEntity;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface IPlanificationService {
    String createPlanification(Planification planification);
    Map<String, Object> getPlanificationWithPlatDetails(Long planificationId);
    List<Map<String, Object>> getAllPlanificationsWithDetails();
    Planification updatePlanification(Long id, Planification updatedPlanification);
    String deletePlanification(Long id);
}
