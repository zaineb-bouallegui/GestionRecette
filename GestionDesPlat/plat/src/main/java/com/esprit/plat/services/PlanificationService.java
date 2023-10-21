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

import java.util.Date;
import java.util.List;
import java.util.Optional;

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
            return "Planification réussie"+plat; // Corrected message
        } else {
            return "Planification non réussie"+planification.getId(); // Corrected message
        }
    }



    @Override
    public Planification getPlanificationById(Long id) {
        return planificationRepository.findById(id).orElse(null);
    }

    @Override
    public List<Planification> getAllPlanifications() {
        return planificationRepository.findAll();
    }

    @Override
    public Planification updatePlanification(Long id, Planification planification) {
        if (planificationRepository.existsById(id)) {
            planification.setId(id);
            return planificationRepository.save(planification);
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
