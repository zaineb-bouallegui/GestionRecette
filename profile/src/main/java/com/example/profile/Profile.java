package com.example.profile;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class Profile implements Serializable {
    private static final long serialVersionUID = 6711457437559348053L;

    @Id
    @GeneratedValue
    private int id;

    private String nom, prenom,img;

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getImg() {
        return img;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setImg(String img) {
        this.img = img;
    }
    public Profile() {
        super();

    }
    public Profile(String nom ) {
        super();
        this.nom = nom;
    }

}