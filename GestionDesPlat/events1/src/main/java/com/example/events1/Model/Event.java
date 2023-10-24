package com.example.events1.Model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
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
public class Event implements Serializable {
    private static final long serialVersionUID = 1L;


    @Id()
    @GeneratedValue
    private Integer id;

    private String title;

    private String description;

    private String lieu;

    private String event_theme;

    @NotNull
    private Integer Nbre_participant ;

    private String imageUrl;

    //private String cuisine;

    private Date date_event = new Date();

}