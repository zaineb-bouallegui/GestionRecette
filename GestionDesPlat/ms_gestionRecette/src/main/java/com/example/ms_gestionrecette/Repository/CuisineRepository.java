package com.example.ms_gestionrecette.Repository;

import com.example.ms_gestionrecette.Model.Cuisine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;

@Repository
public interface CuisineRepository extends JpaRepository<Cuisine,Integer> {

}
