package com.esprit.plat.services;

import com.esprit.plat.repositories.PlatRepository;
import com.esprit.plat.repositories.RecetteRepository; // Import RecetteRepository
import com.esprit.plat.entities.Plat;
import com.esprit.plat.entities.Recette; // Import Recette entity
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlatService implements IPlatService {
    private final PlatRepository platRepository;
    private final RecetteRepository recetteRepository; // Inject RecetteRepository

    @Autowired
    public PlatService(PlatRepository platRepository, RecetteRepository recetteRepository) {
        this.platRepository = platRepository;
        this.recetteRepository = recetteRepository;
    }

    @Override
    public List<Plat> addPlats(List<Plat> plats) {
        return platRepository.saveAll(plats);
    }


    @Override
    public Plat updatePlat(Long id, Plat plat) {
        Optional<Plat> optionalPlat = platRepository.findById(id);
        if (optionalPlat.isPresent()) {
            Plat existingPlat = optionalPlat.get();
            existingPlat.setNom(plat.getNom());
            existingPlat.setDescription(plat.getDescription());
            existingPlat.setCategorie(plat.getCategorie());

            return platRepository.save(existingPlat);
        } else {
            return null;
        }
    }

    @Override
    public String deletePlat(Long id) {
        Optional<Plat> optionalPlat = platRepository.findById(id);
        if (optionalPlat.isPresent()) {
            platRepository.deleteById(id);
            return "Plat supprimé";
        } else {
            return "Plat non supprimé";
        }
    }

    @Override
    public List<Plat> getAllPlats() {
        return platRepository.findAll();
    }

    @Override
    public Plat getPlatById(Long id) {
        return platRepository.findById(id).orElse(null);
    }

    public String assignRecetteToPlat(Long platId, Long recetteId) {
        Plat plat = platRepository.findById(platId).orElse(null);
        Recette recette = recetteRepository.findById(recetteId).orElse(null);

        if (plat != null && recette != null) {
            plat.setRecette(recette); // Assign the recette to the plat
            platRepository.save(plat); // Save the updated plat with the assigned recette
            recette.setPlat(plat);
            recetteRepository.save(recette);
            return "Recette affectée au plat";
        } else {
            return "Recette non affectée au plat"; // Handle the case where either the plat or recette is not found
        }
    }

}


