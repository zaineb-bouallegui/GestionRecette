package com.example.ms_gestionrecette.Repository;

import com.example.ms_gestionrecette.Model.Ingredient;
import com.example.ms_gestionrecette.Model.Recette;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IngredientsRepository extends JpaRepository<Ingredient,Integer> {

}
