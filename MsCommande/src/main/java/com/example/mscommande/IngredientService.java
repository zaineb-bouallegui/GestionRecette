package com.example.mscommande;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IngredientService {

    @Autowired
    private IngredientRepository ingredientRepository;

    public Ingredient addIngrentient(Ingredient ingredient) {
        return ingredientRepository.save(ingredient);
    }

    public List<Ingredient> getAllIngredients() {
        return ingredientRepository.findAll();
    }
    public Ingredient updateIngredient(int id, Ingredient newIngredient) {
        if (ingredientRepository.findById(id).isPresent()) {
            Ingredient existingIngredient = ingredientRepository.findById(id).get();
            existingIngredient.setNom(newIngredient.getNom());
            existingIngredient.setQuantite(newIngredient.getQuantite());
            existingIngredient.setUniteDeMesure(newIngredient.getUniteDeMesure());
            existingIngredient.setImageUrl(newIngredient.getImageUrl());

            return ingredientRepository.save(existingIngredient);
        } else
            return null;
    }

    public String deleteIngredient(int id) {
        if (ingredientRepository.findById(id).isPresent()) {
            ingredientRepository.deleteById(id);
            return "Ingredient supprimé";
        } else
            return "Ingredientnon supprimé";
    }

    public List<Ingredient> getIngredientsByName(String name) {
        return ingredientRepository.findByNom(name);
    }

    public Optional<Ingredient> getIngredientById(int id) {
        return ingredientRepository.findById(id);
    }
}
