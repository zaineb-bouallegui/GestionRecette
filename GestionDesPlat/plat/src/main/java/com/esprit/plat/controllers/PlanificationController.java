package com.esprit.plat.controllers;

import com.esprit.plat.entities.Planification;
import com.esprit.plat.services.PlanificationService;
import com.esprit.plat.services.PlatService;
import org.keycloak.KeycloakPrincipal;
import org.keycloak.KeycloakSecurityContext;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

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
    public ResponseEntity<String> createPlanification(@RequestBody Planification planification,  KeycloakAuthenticationToken auth) {
        KeycloakPrincipal<KeycloakSecurityContext> principal = (KeycloakPrincipal<KeycloakSecurityContext>) auth.getPrincipal();
        KeycloakSecurityContext context = principal.getKeycloakSecurityContext();
        boolean hasUserRole = context.getToken().getRealmAccess().isUserInRole("user");
        System.out.println("role:"+hasUserRole);
        if(hasUserRole) {
        String result = planificationService.createPlanification(planification);

        if (result.equals("Planification réussie")) {
            return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
        }
    }
    else {
    return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }
    }



    @GetMapping(value="/{planificationId}")
    public Map<String, Object> getPlanificationWithPlatDetails(@PathVariable Long planificationId) {
        return planificationService.getPlanificationWithPlatDetails(planificationId);
    }


    @GetMapping()
    public List<Map<String, Object>> getAllPlanificationsWithDetails() {
        return planificationService.getAllPlanificationsWithDetails();
    }


    @GetMapping("/userPlans")
    public List<Map<String, Object>> getPlanificationsWithDetailsForUser(
            @RequestParam Long userId) {
        return planificationService.getPlanificationsWithDetailsForUser(userId);
    }

   @PutMapping("/{id}")
    public ResponseEntity<Object> updatePlanification(@PathVariable Long id, @RequestBody Planification planification, KeycloakAuthenticationToken auth) {
        KeycloakPrincipal<KeycloakSecurityContext> principal = (KeycloakPrincipal<KeycloakSecurityContext>) auth.getPrincipal();
        KeycloakSecurityContext context = principal.getKeycloakSecurityContext();
        boolean hasUserRole = context.getToken().getRealmAccess().isUserInRole("user");
        System.out.println("role:" + hasUserRole);

        if (!hasUserRole) {
            // The user doesn't have the required role, so return a 403 Forbidden response.
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        Planification updatedPlanification = planificationService.updatePlanification(id, planification);

        if (updatedPlanification != null) {
            return ResponseEntity.ok(updatedPlanification);
        } else {
            return ResponseEntity.ok("Update failed"); // You can return a custom error message here
        }
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePlanification(@PathVariable Long id, KeycloakAuthenticationToken auth) {
        KeycloakPrincipal<KeycloakSecurityContext> principal = (KeycloakPrincipal<KeycloakSecurityContext>) auth.getPrincipal();
        KeycloakSecurityContext context = principal.getKeycloakSecurityContext();
        boolean hasUserRole = context.getToken().getRealmAccess().isUserInRole("user");

        if (hasUserRole) {
            String result = planificationService.deletePlanification(id);
            if ("Planification deleted".equals(result)) {
                return ResponseEntity.ok(result);
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(result);
            }
        } else {
            // If the user doesn't have the required role, return a 403 Forbidden response.
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("You do not have the required role to delete the planification.");
        }
    }
}
