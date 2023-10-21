package com.esprit.plat.repositories;


import com.esprit.plat.entities.Recette;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecetteRepository extends JpaRepository<Recette ,Long> {
    // Vous pouvez ajouter des méthodes personnalisées ici si nécessaire
}
