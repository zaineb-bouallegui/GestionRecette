package com.esprit.plat.entities;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    //@JsonIgnore
    @OneToOne(optional = true, fetch = FetchType.EAGER)
    private Recette recette;
    @OneToMany(mappedBy = "plat")
    //@JsonManagedReference
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
