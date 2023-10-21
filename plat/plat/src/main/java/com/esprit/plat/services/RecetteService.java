package com.esprit.plat.services;

import com.esprit.plat.entities.Recette;
import com.esprit.plat.repositories.RecetteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecetteService implements IRecetteService{

    private final RecetteRepository recetteRepository;

    @Autowired
    public RecetteService(RecetteRepository recetteRepository) {
        this.recetteRepository = recetteRepository;
    }
    @Override
    public Recette addRecette(Recette recette) {
        return recetteRepository.save(recette);
    }
}
