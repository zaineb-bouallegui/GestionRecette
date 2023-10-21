package com.example.ms_gestionrecette.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Recette implements Serializable {
    private static final long serialVersionUID = 1L;


    @Id()
    @GeneratedValue
    private Integer id;

    private String title;

    private String description;

    private String instructions;

    private String imageUrl;

    private Category category;

    //private String cuisine;

    private Date createdAt = new Date();

    private Integer averageRating;

    private Integer createdByUserId;


    public Integer likes=0;


    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER)
    private Set<Ingredient> ingredients;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    private Cuisine cuisine;



}
