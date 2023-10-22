package com.esprit.plat.services;

import com.esprit.plat.entities.Planification;
import com.esprit.plat.entities.Plat;
import com.esprit.plat.entities.Recette;
import com.esprit.plat.repositories.PlanificationRepository;
import com.esprit.plat.repositories.PlatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

@Service
public class PlanificationService implements IPlanificationService {

    private final PlanificationRepository planificationRepository;
    private final PlatRepository platRepository;
    @Autowired
    public PlanificationService(PlanificationRepository planificationRepository, PlatRepository platRepository) {
        this.planificationRepository = planificationRepository;
        this.platRepository = platRepository;
    }




    @PostMapping
    public String createPlanification(@RequestBody Planification planification) {
        Planification newPlanification = new Planification();

        Plat plat = platRepository.findById(planification.getId()).orElse(null);
        Long idUser = planification.getUserId();
        Date date = planification.getPlanDateTime();

        System.out.println("Plat"+plat);
        System.out.println("IdUser"+idUser);
        System.out.println("Date"+date);
        if (plat != null && idUser != null && date != null) {
            newPlanification.setPlat(plat);
            newPlanification.setPlanDateTime(date);
            newPlanification.setUserId(idUser);

            planificationRepository.save(newPlanification);
            return "Planification réussie";
        } else {
            return "Planification non réussie"+planification.getId(); // Corrected message
        }
    }



    @Override
    public Map<String, Object> getPlanificationWithPlatDetails(Long planificationId) {
        Planification planification = planificationRepository.findById(planificationId).orElse(null);
        Map<String, Object> result = new HashMap<>();

        if (planification != null) {
            result.put("id", planification.getId());
            result.put("userId", planification.getUserId());
            result.put("planDateTime", planification.getPlanDateTime());

            Plat plat = planification.getPlat();
            if (plat != null) {
                result.put("platName", plat.getNom());
                result.put("platDescription", plat.getDescription());
                result.put("platCategorie", plat.getCategorie());
                // Add other plat details as needed
            }
        }

        return result;
    }

    @Override
    public List<Map<String, Object>> getAllPlanificationsWithDetails() {
        List<Planification> planifications = planificationRepository.findAll();
        List<Map<String, Object>> result = new ArrayList<>();

        for (Planification planification : planifications) {
            Map<String, Object> planificationMap = new HashMap<>();
            planificationMap.put("id", planification.getId());
            planificationMap.put("userId", planification.getUserId());
            planificationMap.put("planDateTime", planification.getPlanDateTime());

            // Add plat details from the associated Plat entity
            Plat plat = planification.getPlat();
            if (plat != null) {
                planificationMap.put("platName", plat.getNom());
                planificationMap.put("platDescription", plat.getDescription());
                planificationMap.put("platCategorie", plat.getCategorie());
                // Add other plat details as needed
            }

            result.add(planificationMap);
        }

        return result;
    }

    public List<Map<String, Object>> getPlanificationsWithDetailsForUser(Long userId) {
        List<Planification> planifications = planificationRepository.findByUserId(userId);
        List<Map<String, Object>> result = new ArrayList<>();

        for (Planification planification : planifications) {
            Map<String, Object> planificationMap = new HashMap<>();
            planificationMap.put("id", planification.getId());
            planificationMap.put("userId", planification.getUserId());
            planificationMap.put("planDateTime", planification.getPlanDateTime());

            // Add plat details from the associated Plat entity
            Plat plat = planification.getPlat();
            if (plat != null) {
                planificationMap.put("platName", plat.getNom());
                planificationMap.put("platDescription", plat.getDescription());
                planificationMap.put("platCategorie", plat.getCategorie());
                // Add other plat details as needed
            }

            result.add(planificationMap);
        }

        return result;
    }

    @Override
    public Planification updatePlanification(Long id, Planification updatedPlanification) {
        Planification existingPlanification = planificationRepository.findById(id).orElse(null);

        if (existingPlanification != null) {
            // Update the fields you want to allow from updatedPlanification
            existingPlanification.setPlanDateTime(updatedPlanification.getPlanDateTime());

            // You can add more fields to update if needed

            return planificationRepository.save(existingPlanification);
        }

        return null;
    }



    @Override
    public String deletePlanification(Long id) {
        if (planificationRepository.existsById(id)) {
            planificationRepository.deleteById(id);
            return "Planification deleted";
        }
        return "Planification not found";
    }
}
