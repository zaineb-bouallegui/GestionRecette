package com.example.mscommande;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Ingredient implements Serializable {
    private static final long serialVersionUID = 6711457437559348053L;

    @Id
    @GeneratedValue
    private int id;
    private String nom;
    private double quantite;
    private String uniteDeMesure;
    private String imageUrl;
    private double stock;

    public double getStock() {
        return stock;
    }

    public void setStock(double stock) {
        this.stock = stock;
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public double getQuantite() {
        return quantite;
    }

    public void setQuantite(double quantite) {
        this.quantite = quantite;
    }

    public String getUniteDeMesure() {
        return uniteDeMesure;
    }

    public void setUniteDeMesure(String uniteDeMesure) {
        this.uniteDeMesure = uniteDeMesure;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Ingredient() {
        super();
    }

    public Ingredient(String nom, double quantite, String uniteDeMesure, String imageUrl) {
        super();
        this.nom = nom;
        this.quantite = quantite;
        this.uniteDeMesure = uniteDeMesure;
        this.imageUrl = imageUrl;
        this.stock = stock;
    }
}
