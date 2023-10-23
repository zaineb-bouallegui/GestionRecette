package com.example.mscommande;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IngredientRepository extends JpaRepository< Ingredient , Integer>{
    List<Ingredient> findByNom(String nom);
    List<Ingredient> findByStockGreaterThan(double stock);
    List<Ingredient> findByUniteDeMesure(String uniteDeMesure);
    @Query("SELECT i FROM Ingredient i WHERE i.nom LIKE %:partialName%")
    List<Ingredient> findByPartialName(String partialName);
}
