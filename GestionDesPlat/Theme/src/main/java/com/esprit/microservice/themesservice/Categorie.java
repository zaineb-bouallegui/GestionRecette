package com.esprit.microservice.themesservice;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Categorie implements Serializable {
    private static final long serialVersionUID = 6711457437559348053L;

    @Id
    @GeneratedValue
    private int id;
    private String nom;

    // Une catégorie peut avoir plusieurs thèmes
    @OneToMany(mappedBy = "categorie")
    private List<Theme> themes;

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public List<Theme> getThemes() {
        return themes;
    }

    public void setThemes(List<Theme> themes) {
        this.themes = themes;
    }

    public Categorie() {
        super();
    }

    public Categorie(String nom) {
        super();
        this.nom = nom;
    }
}
