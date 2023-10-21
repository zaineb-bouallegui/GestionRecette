package com.esprit.plat.repositories;

import com.esprit.plat.entities.Plat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlatRepository extends JpaRepository<Plat, Long> {
    // Vous pouvez ajouter des méthodes personnalisées ici si nécessaire
}
