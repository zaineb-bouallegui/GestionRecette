package com.example.ms_gestionrecette.Service;

import com.example.ms_gestionrecette.Model.Category;
import com.example.ms_gestionrecette.Model.Cuisine;
import com.example.ms_gestionrecette.Model.Ingredient;
import com.example.ms_gestionrecette.Model.Recette;

import java.util.List;

public interface RecetteService {

     Recette addRecette(Recette recette);

     Cuisine addCuisineAndAssignToRecette(Cuisine cuisine,Integer idr);

     void deleteCuisine(Integer id);

     Ingredient addIngredientAndAssignToRecette(Ingredient ingredient, Integer idr);

     void deleteIng(Integer id);


     List<Recette> retrieveAllRecipes();
     List<Recette> findRecipeById(Integer id);
   //  List<Recette> findRecipeByTitle(String title);
    // List<Recette> findRecipeByDescription(String description);
    // List<Recette> findRecipeByCategory(String category);
     //List<Recette> findRecipeByCuisine(String cuisine);

     public List<Recette> filterRecipes(String title, String description, Category category , Integer rating,Integer likes,String cuisine);

     String deleteRecipeById(Integer id);
     Recette updateRecipe(Recette recette,Integer id);

   //  List<Recette> filterByRating(Integer rating);

     public Recette updateLike(Integer id);

     public Recette updateDislike(Integer id);


}
