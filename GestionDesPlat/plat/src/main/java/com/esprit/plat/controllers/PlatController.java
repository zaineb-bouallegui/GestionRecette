package com.esprit.plat.controllers;

import com.esprit.plat.entities.Plat;
import com.esprit.plat.services.PlatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<List<Plat>> createPlats(@RequestBody List<Plat> plats) {
        List<Plat> createdPlats = platService.addPlats(plats); // Modify the service method to handle a list of plats
        return new ResponseEntity<>(createdPlats, HttpStatus.CREATED);
    }


    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Plat> updatePlat(@PathVariable(value = "id") Long id, @RequestBody Plat plat) {
        Plat updatedPlat = platService.updatePlat(id, plat);
        if (updatedPlat != null) {
            return new ResponseEntity<>(updatedPlat, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> deletePlat(@PathVariable(value = "id") Long id) {
        String result = platService.deletePlat(id);
        if (result.equals("Plat supprimé")) {
            return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public List<Plat> getAllPlats() {
        return platService.getAllPlats();
    }

    @PostMapping(value = "/{platId}/assign-recette/{recetteId}")
    public ResponseEntity<String> assignRecetteToPlat(@PathVariable(value = "platId") Long platId, @PathVariable(value = "recetteId") Long recetteId) {
        String result = platService.assignRecetteToPlat(platId, recetteId);
        if (result.equals("Recette affectée au plat")) {
            return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
        }
    }
}
