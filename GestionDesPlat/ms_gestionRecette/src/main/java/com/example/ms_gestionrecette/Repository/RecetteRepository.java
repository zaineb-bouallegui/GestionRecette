package com.example.ms_gestionrecette.Repository;

import com.example.ms_gestionrecette.Model.Category;
import com.example.ms_gestionrecette.Model.Recette;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecetteRepository extends JpaRepository<Recette,Integer> {
    List<Recette> findRecettesById(Integer id);

    List<Recette> findRecettesByTitleContaining(String title);
    List<Recette> findRecettesByDescription(String description);
    List<Recette> findRecettesByCategoryContaining(String category);
    //List<Recette> findRecettesByCuisineContaining(String cuisine);

    List<Recette> findByDescriptionContaining(String description);


    List<Recette> findByTitleContainingAndDescriptionContainingAndCategoryAndAverageRatingGreaterThanEqualAndLikesIsGreaterThanEqual(String title, String description, Category category, Integer rating,Integer likes);

    @Query("SELECT r FROM Recette r WHERE r.cuisine.name = :cuisineName")
    List<Recette> findByCuisineName(@Param("cuisineName") String cuisineName);



    @Query("SELECT r FROM Recette r WHERE r.averageRating >= :minRating")
    List<Recette> findByAverageRatingGreaterThanEqual(@Param("minRating") Integer minRating);

}
