package com.esprit.microservice.themesservice;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity
public class Theme implements Serializable{
	private static final long serialVersionUID = 6711457437559348053L;



	@Id
	@GeneratedValue
	private int id;
	private String titre,description;
	private int nombre_rct;
	public int getId() {
		return id;
	}



	public int getNombre_rct() {
		return nombre_rct;
	}

	public void setNombre_rct(int nombre_rct) {
		this.nombre_rct = nombre_rct;
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

	public Theme() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Theme(String titre) {
		super();
		this.titre = titre;
	}


}
