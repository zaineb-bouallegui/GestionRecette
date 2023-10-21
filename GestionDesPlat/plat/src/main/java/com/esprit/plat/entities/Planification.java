package com.esprit.plat.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Planification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Store the ID of the user who planned the meal
    private Long userId;

    // Reference to the planned meal (you can customize this based on your application)


    @ManyToOne
    @JoinColumn(name = "plat_id")
    private Plat plat;

    // Date and time of the meal plan
    private Date planDateTime;

    // Constructors, getters, and setters

    public Planification() {
        // Default constructor
    }

    public Planification(Long userId, Plat plat, Date planDateTime) {
        this.userId = userId;
        this.plat = plat;
        this.planDateTime = planDateTime;
    }

    // Getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Plat getPlat() {
        return plat;
    }

    public void setPlat(Plat plat) {
        this.plat = plat;
    }

    public Date getPlanDateTime() {
        return planDateTime;
    }

    public void setPlanDateTime(Date planDateTime) {
        this.planDateTime = planDateTime;
    }
}
