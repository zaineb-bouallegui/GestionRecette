package com.esprit.plat.controllers;

import com.esprit.plat.entities.Plat;
import com.esprit.plat.services.PlatService;
import org.keycloak.KeycloakPrincipal;
import org.keycloak.KeycloakSecurityContext;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/plats")
public class PlatController {
    private String title = "Hello, I'm the plat Microservice";
    @Autowired
    private PlatService platService;

    @RequestMapping("/hello")
    public String sayHello() {
        System.out.println(title);
        return title;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<List<Plat>> createPlats(@RequestBody List<Plat> plats, KeycloakAuthenticationToken auth) {
        KeycloakPrincipal<KeycloakSecurityContext> principal = (KeycloakPrincipal<KeycloakSecurityContext>) auth.getPrincipal();
        KeycloakSecurityContext context = principal.getKeycloakSecurityContext();
        boolean hasUserRole = context.getToken().getRealmAccess().isUserInRole("admin");
        System.out.println(hasUserRole);
        if (hasUserRole) {
            // Modify the platService.addPlats method to handle a list of plats
            List<Plat> createdPlats = platService.addPlats(plats);

            if (createdPlats != null && !createdPlats.isEmpty()) {
                return new ResponseEntity<>(createdPlats, HttpStatus.CREATED);
            } else {

                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }


    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Plat> updatePlat(@PathVariable(value = "id") Long id, @RequestBody Plat plat, KeycloakAuthenticationToken auth) {
        KeycloakPrincipal<KeycloakSecurityContext> principal = (KeycloakPrincipal<KeycloakSecurityContext>) auth.getPrincipal();
        KeycloakSecurityContext context = principal.getKeycloakSecurityContext();
        boolean hasUserRole = context.getToken().getRealmAccess().isUserInRole("admin");

        if (hasUserRole) {
            Plat updatedPlat = platService.updatePlat(id, plat);
            if (updatedPlat != null) {
                return new ResponseEntity<>(updatedPlat, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }


    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> deletePlat(@PathVariable(value = "id") Long id, KeycloakAuthenticationToken auth) {
        KeycloakPrincipal<KeycloakSecurityContext> principal = (KeycloakPrincipal<KeycloakSecurityContext>) auth.getPrincipal();
        KeycloakSecurityContext context = principal.getKeycloakSecurityContext();
        boolean hasUserRole = context.getToken().getRealmAccess().isUserInRole("admin");

        if (hasUserRole) {
            String result = platService.deletePlat(id);
            if (result.equals("Plat supprimé")) {
                return new ResponseEntity<>(result, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }


    @GetMapping
    public List<Map<String, Object>> getAllPlatsWithRecetteDetails() {
        return platService.getAllPlatsWithRecetteDetails();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Plat> getPlatWithRecetteDetails(@PathVariable Long id) {
        Plat plat = platService.getPlatByIdWithRecetteDetails(id);

        if (plat != null) {
            return ResponseEntity.ok(plat);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping(value = "/{platId}/assign-recette/{recetteId}")
    public ResponseEntity<String> assignRecetteToPlat(@PathVariable(value = "platId") Long platId, @PathVariable(value = "recetteId") Long recetteId, KeycloakAuthenticationToken auth) {
        KeycloakPrincipal<KeycloakSecurityContext> principal = (KeycloakPrincipal<KeycloakSecurityContext>) auth.getPrincipal();
        KeycloakSecurityContext context = principal.getKeycloakSecurityContext();
        boolean hasUserRole = context.getToken().getRealmAccess().isUserInRole("admin");

        if (hasUserRole) {
            String result = platService.assignRecetteToPlat(platId, recetteId);
            if (result.equals("Recette affectée au plat")) {
                return new ResponseEntity<>(result, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

}
