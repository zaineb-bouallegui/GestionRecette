package com.esprit.microservice.themesservice;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CategorieRepository extends JpaRepository<Categorie, Integer> {
    // You can add custom query methods if needed
}