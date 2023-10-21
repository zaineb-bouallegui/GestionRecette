package com.esprit.plat.controllers;
import com.esprit.plat.services.RecetteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import com.esprit.plat.entities.Recette;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/recettes")
public class RecetteController {
    private String title = "Hello, I'm the recette Microservice";
    private final RecetteService recetteService;

    @Autowired
    public RecetteController(RecetteService recetteService) {
        this.recetteService = recetteService;
    }

    @RequestMapping("/hello")
    public String sayHello() {
        System.out.println(title);
        return title;
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Recette> createRecette(@RequestBody Recette recette) {
        return new ResponseEntity<>(recetteService.addRecette(recette), HttpStatus.OK);
    }
}
