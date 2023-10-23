package com.example.ms_gestionrecette.Controller;

import com.example.ms_gestionrecette.Model.Category;
import com.example.ms_gestionrecette.Model.Cuisine;
import com.example.ms_gestionrecette.Model.Ingredient;
import com.example.ms_gestionrecette.Model.Recette;
import com.example.ms_gestionrecette.Service.RecetteService;
import org.keycloak.KeycloakPrincipal;
import org.keycloak.KeycloakSecurityContext;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/recette")
public class RecetteController {

    @Autowired
    private RecetteService recetteService;

    @PostMapping("/add")
    @ResponseStatus
    public ResponseEntity<Recette> createRecette(@RequestBody Recette recette, KeycloakAuthenticationToken auth){
        KeycloakPrincipal<KeycloakSecurityContext> principal = (KeycloakPrincipal<KeycloakSecurityContext>) auth.getPrincipal();
        KeycloakSecurityContext context = principal.getKeycloakSecurityContext();
        boolean hasUserRole = context.getToken().getRealmAccess().isUserInRole("user");

        if (hasUserRole) {
            return new ResponseEntity<>(recetteService.addRecette(recette), HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @PutMapping("/addCuisine/{idr}")
    @ResponseStatus
    public ResponseEntity<Cuisine> createCuisine(@RequestBody Cuisine cuisine,@PathVariable("idr") Integer idr){
        return new ResponseEntity<>(recetteService.addCuisineAndAssignToRecette(cuisine,idr), HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/deleteCuisine/{id}")
    @ResponseStatus
    public void deleteCui(@PathVariable("id") Integer id){
        recetteService.deleteCuisine(id);
    }

    @PutMapping("/addIng/{idr}")
    @ResponseStatus
    public ResponseEntity<Ingredient> createIngredient(@RequestBody Ingredient ingredient, @PathVariable("idr") Integer idr){
        return new ResponseEntity<>(recetteService.addIngredientAndAssignToRecette(ingredient,idr), HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/deleteIng/{id}")
    @ResponseStatus
    public void deleteIng(@PathVariable("id") Integer id){
        recetteService.deleteIng(id);
    }

    @GetMapping("/getAll")
    @ResponseStatus
    public List<Recette> getAllRecipes(){
        return recetteService.retrieveAllRecipes();
    }

    @GetMapping("/getById/{id}")
    @ResponseStatus
    public List<Recette> getRecipesById(@PathVariable("id")Integer id){
        return recetteService.findRecipeById(id);
    }

  /*  @GetMapping("/getByTitle/{t}")
    @ResponseStatus
    public List<Recette> getRecipesByTitle(@PathVariable("t")String t){
        return recetteService.findRecipeByTitle(t);
    }

    @GetMapping("/getByDescription/{t}")
    @ResponseStatus
    public List<Recette> getRecipesByDescription(@PathVariable("t")String t){
        return recetteService.findRecipeByDescription(t);
    }

    @GetMapping("/getByCategory/{t}")
    @ResponseStatus
    public List<Recette> getRecipesByCategory(@PathVariable("t")String t){
        return recetteService.findRecipeByCategory(t);
    }

    @GetMapping("/getByCuisine/{t}")
    @ResponseStatus
    public List<Recette> getRecipesByCuisine(@PathVariable("t")String t){
        return recetteService.findRecipeByCuisine(t);
    }

 */


    @GetMapping(value="/filter")
    public List<Recette> filterRecipes(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String description,
            @RequestParam(required = false) Category category,
            @RequestParam(required = false) Integer rating,
            @RequestParam(required = false) Integer likes,
            @RequestParam(required = false) String cuisine

            ) {
        return recetteService.filterRecipes(title, description, category , rating,likes,cuisine);
    }


    @PutMapping( value = "/update/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus
    public ResponseEntity<Recette> updateRecipe(@RequestBody Recette recette,@PathVariable("id") Integer id){
        return new ResponseEntity<>(recetteService.updateRecipe(recette,id), HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public  ResponseEntity<String> deleteRecette(@PathVariable("id") Integer id , KeycloakAuthenticationToken auth){
        KeycloakPrincipal<KeycloakSecurityContext> principal = (KeycloakPrincipal<KeycloakSecurityContext>) auth.getPrincipal();
        KeycloakSecurityContext context = principal.getKeycloakSecurityContext();
        boolean hasUserRole = context.getToken().getRealmAccess().isUserInRole("admin");
        if (hasUserRole) {
            return new ResponseEntity<>(recetteService.deleteRecipeById(id), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

   /* @GetMapping("/rating/{rating}")
    @ResponseStatus
    public List<Recette> getRecipesByDescription(@PathVariable("rating")Integer rating){
        return recetteService.filterByRating(rating);
    }*/


    @PutMapping( value = "/updateLike/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus
    public ResponseEntity<Recette> updateRecetteLike(@PathVariable("id") Integer id){
        return new ResponseEntity<>(recetteService.updateLike(id), HttpStatus.OK);
    }

    @PutMapping( value = "/updateDislike/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus
    public ResponseEntity<Recette> updateRecetteDislike(@PathVariable("id") Integer id){
        return new ResponseEntity<>(recetteService.updateDislike(id), HttpStatus.OK);
    }

}
