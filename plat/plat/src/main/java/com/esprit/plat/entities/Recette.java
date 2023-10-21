package com.esprit.plat.entities;

import javax.persistence.*;

@Entity
public class Recette {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String titre;
        private String description;

        @OneToOne(mappedBy = "recette", optional = true)
        private Plat plat;

        // Constructors
        public Recette() {
                // Default constructor
        }

        public Recette(String titre, String description) {
                this.titre = titre;
                this.description = description;
        }

        // Copy constructor
        public Recette(Recette otherRecette) {
                this.titre = otherRecette.titre;
                this.description = otherRecette.description;
        }

        // Getters and setters
        public Long getId() {
                return id;
        }

        public void setId(Long id) {
                this.id = id;
        }

        public String getTitre() {
                return titre;
        }

        public void setTitre(String titre) {
                this.titre = titre;
        }

        public String getDescription() {
                return description;
        }

        public void setDescription(String description) {
                this.description = description;
        }

        public Plat getPlat() {
                return plat;
        }

        public void setPlat(Plat plat) {
                this.plat = plat;
        }
}
