package com.example.ms_gestionrecette.Service;

import com.example.ms_gestionrecette.Model.Category;
import com.example.ms_gestionrecette.Model.Cuisine;
import com.example.ms_gestionrecette.Model.Ingredient;
import com.example.ms_gestionrecette.Model.Recette;
import com.example.ms_gestionrecette.Repository.CuisineRepository;
import com.example.ms_gestionrecette.Repository.IngredientsRepository;
import com.example.ms_gestionrecette.Repository.RecetteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class RecetteServiceImpl implements RecetteService {

    @Autowired
    private RecetteRepository recetteRepository;

    @Autowired
    private CuisineRepository cuisineRepository;
    @Autowired
    private IngredientsRepository ingredientsRepository;

    @Override
    public Recette addRecette(Recette recette) {
        return recetteRepository.save(recette);
    }

    @Override
    public Cuisine addCuisineAndAssignToRecette(Cuisine cuisine,Integer idr) {
        cuisineRepository.save(cuisine);
        Recette recette=recetteRepository.findById(idr).orElse(null);
        recette.setCuisine(cuisine);
        recetteRepository.save(recette);
        return cuisine;
    }///in case I want to add a new cuisine in the list of cuisines

    @Override
    public void deleteCuisine(Integer id) {
        cuisineRepository.deleteById(id);
    }

    @Override
    public Ingredient addIngredientAndAssignToRecette(Ingredient ingredient, Integer idr) {
        ingredientsRepository.save(ingredient);
        Recette recette=recetteRepository.findById(idr).orElse(null);
        recette.getIngredients().add(ingredient);
        recetteRepository.save(recette);
        return ingredient;


    }

    @Override
    public void deleteIng(Integer id) {
        ingredientsRepository.deleteById(id);
    }

    @Override
    public List<Recette> retrieveAllRecipes() {
        return recetteRepository.findAll();
    }

    @Override
    public List<Recette> findRecipeById(Integer id) {
        return recetteRepository.findRecettesById(id);
    }
/*
    @Override
    public List<Recette> findRecipeByTitle(String title) {
        return recetteRepository.findRecettesByTitleContaining(title);
    }

    @Override
    public List<Recette> findRecipeByDescription(String description) {
        return recetteRepository.findByDescriptionContaining(description);
    }

    @Override
    public List<Recette> findRecipeByCategory(String category) {
        return recetteRepository.findRecettesByCategoryContaining(category);
    }
*/
    @Override
    public List<Recette> filterRecipes(String title, String description, Category category , Integer rating,Integer likes,String cuisine) {
        List<Recette> recetteList= recetteRepository.findByTitleContainingAndDescriptionContainingAndCategoryAndAverageRatingGreaterThanEqualAndLikesIsGreaterThanEqual(title, description, category , rating,likes);
        if (cuisine != null && !cuisine.isEmpty()) {
            recetteList = recetteRepository.findByCuisineName(cuisine);
        }
        return recetteList;
    }


/*
    @Override
    public List<Recette> findRecipeByCuisine(String cuisine) {
        return recetteRepository.findRecettesByCuisineContaining(cuisine);
    }

 */

    @Override
    public String deleteRecipeById(Integer id) {
        recetteRepository.deleteById(id);
        if (recetteRepository.findById(id).isPresent()){
            Recette existingR = recetteRepository.findById(id).get();
            recetteRepository.delete(existingR);
            return "Introuvable !";
        }

        return "Recette deleted successfully !";
    }

    public Recette updateRecipe(Recette recette,Integer id) {
        if (recetteRepository.findById(id).isPresent()) {
            Recette existingRecette = recetteRepository.findById(id).get();
            existingRecette.setTitle(recette.getTitle());
            existingRecette.setDescription(recette.getDescription());
            existingRecette.setInstructions(recette.getInstructions());
            existingRecette.setImageUrl(recette.getImageUrl());
            existingRecette.setCategory(recette.getCategory());
            existingRecette.setCuisine(recette.getCuisine());


            return recetteRepository.save(existingRecette);
        }
        return null;
    }
/*
    @Override
    public List<Recette> filterByRating(Integer rating) {
        return recetteRepository.findByAverageRatingGreaterThanEqual(rating);
    }
*/
    @Override
    public Recette updateLike(Integer id) {
        if (recetteRepository.findById(id).isPresent()) {
            Recette existingRecette = recetteRepository.findById(id).get();
            existingRecette.setLikes(existingRecette.likes+1);
            return recetteRepository.save(existingRecette);
        }
        return null;
    }

    @Override
    public Recette updateDislike(Integer id) {
        if (recetteRepository.findById(id).isPresent()) {
            Recette existingRecette = recetteRepository.findById(id).get();
            if (existingRecette.likes >0 ) {
                existingRecette.setLikes(existingRecette.likes-1);
                return recetteRepository.save(existingRecette);

            }
            existingRecette.setLikes(existingRecette.likes);
            return  recetteRepository.save(existingRecette);

        }
        return null;
    }


}
