package com.example.ms_gestionrecette.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Ingredient implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id()
    @GeneratedValue
    private Integer id;
    private String name;

    private Integer quantity;

    @JsonIgnore
    @ManyToOne
    private Recette recette;

}
