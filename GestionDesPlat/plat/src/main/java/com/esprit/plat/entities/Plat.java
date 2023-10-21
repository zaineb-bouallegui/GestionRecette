package com.esprit.plat.entities;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
public class Plat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String description;
    private CategoriePlat categorie;
    @OneToOne(optional = true)
    private Recette recette;
    @OneToMany(mappedBy = "plat")
    private List<Planification> planifications;

    // Énumération pour les catégories (sucré ou salé)
    public enum CategoriePlat {
        SUCRE,
        SALE
    }

    // Constructeur par défaut
    public Plat() {
    }

    // Constructeur avec les champs obligatoires
    public Plat(String nom, CategoriePlat categorie) {
        this.nom = nom;
        this.categorie = categorie;
    }

    // Constructeur avec tous les champs
    public Plat(String nom, String description, CategoriePlat categorie) {
        this.nom = nom;
        this.description = description;
        this.categorie = categorie;
    }
}
