package com.example.mscommande;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IngredientRepository extends JpaRepository< Ingredient , Integer>{
    List<Ingredient> findByNom(String nom);

}
