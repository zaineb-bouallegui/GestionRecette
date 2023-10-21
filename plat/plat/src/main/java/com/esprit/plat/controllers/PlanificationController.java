package com.esprit.plat.controllers;

import com.esprit.plat.entities.Planification;
import com.esprit.plat.services.PlanificationService;
import com.esprit.plat.services.PlatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/planifications")
public class PlanificationController {

    private final PlanificationService planificationService;
    @Autowired
    private PlatService platService;

    @Autowired
    public PlanificationController(PlanificationService planificationService) {
        this.planificationService = planificationService;
    }

    @PostMapping
    public ResponseEntity<String> createPlanification(@RequestBody Planification planification) {
        String result = planificationService.createPlanification(planification);

        if (result.equals("Planification réussie")) {
            return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
        }
    }



    @GetMapping("/{id}")
    public Planification getPlanificationById(@PathVariable Long id) {
        return planificationService.getPlanificationById(id);
    }

    @GetMapping
    public List<Planification> getAllPlanifications() {
        return planificationService.getAllPlanifications();
    }

    @PutMapping("/{id}")
    public Planification updatePlanification(@PathVariable Long id, @RequestBody Planification planification) {
        return planificationService.updatePlanification(id, planification);
    }

    @DeleteMapping("/{id}")
    public String deletePlanification(@PathVariable Long id) {
        return planificationService.deletePlanification(id);
    }
}
