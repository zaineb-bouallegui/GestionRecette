package com.esprit.microservice.themesservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategorieService {

    @Autowired
    private CategorieRepository categorieRepository;

    // Create a new Categorie
    public Categorie addCategorie(Categorie categorie) {
        return categorieRepository.save(categorie);
    }

    // Update an existing Categorie
    public Categorie updateCategorie(int id, Categorie updatedCategorie) {
        Categorie existingCategorie = categorieRepository.findById(id).orElse(null);

        if (existingCategorie != null) {
            // Update the fields of the existing Categorie with the new values
            existingCategorie.setNom(updatedCategorie.getNom());

            return categorieRepository.save(existingCategorie);
        } else {
            // Handle the case where the Categorie with the given ID does not exist
            return null;
        }
    }

    // Delete a Categorie
    public String deleteCategorie(int id) {
        Categorie existingCategorie = categorieRepository.findById(id).orElse(null);

        if (existingCategorie != null) {
            categorieRepository.delete(existingCategorie);
            return "Categorie avec ID " + id + " est supprimée.";
        } else {
            // Handle the case where the Categorie with the given ID does not exist
            return "Categorie not found.";
        }
    }

    // Get all Categories
    public List<Categorie> getAllCategories() {
        return categorieRepository.findAll();
    }

    // Get a specific Categorie by ID
    public Categorie getCategorieById(int id) {
        return categorieRepository.findById(id).orElse(null);
    }
}
