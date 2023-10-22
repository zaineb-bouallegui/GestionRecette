package com.esprit.plat.services;

import com.esprit.plat.repositories.PlatRepository;
import com.esprit.plat.repositories.RecetteRepository; // Import RecetteRepository
import com.esprit.plat.entities.Plat;
import com.esprit.plat.entities.Recette; // Import Recette entity
import org.springframework.beans.factory.annotation.Autowired;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PlatService implements IPlatService {
    private final PlatRepository platRepository;
    private final RecetteRepository recetteRepository; // Inject RecetteRepository

    @PersistenceContext
    private EntityManager entityManager;
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
    public List<Map<String, Object>> getAllPlatsWithRecetteDetails() {
        String sql = "SELECT p.id AS platId, p.nom AS platName, p.description AS platDescription, " +
                "p.categorie AS platCategorie, r.id AS recetteId, r.titre AS recetteTitre, " +
                "r.description AS recetteDescription " +
                "FROM Plat p " +
                "LEFT JOIN Recette r ON p.recette_id = r.id";

        Query query = entityManager.createNativeQuery(sql);
        List<Object[]> results = query.getResultList();
        List<Map<String, Object>> platDetailsList = new ArrayList<>();

        for (Object[] result : results) {
            Map<String, Object> platDetails = new HashMap<>();
            platDetails.put("id", result[0]);
            platDetails.put("platName", result[1]);
            platDetails.put("platDescription", result[2]);
            platDetails.put("platCategorie", result[3]);
            platDetails.put("recetteId", result[4]);
            platDetails.put("recetteTitre", result[5]);
            platDetails.put("recetteDescription", result[6]);

            platDetailsList.add(platDetails);
        }

        return platDetailsList;
    }

    @Override
    public Plat getPlatByIdWithRecetteDetails(Long id) {
        return platRepository.findPlatWithRecette(id);
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


