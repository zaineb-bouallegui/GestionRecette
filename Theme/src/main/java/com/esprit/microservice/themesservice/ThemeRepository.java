package com.esprit.microservice.themesservice;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ThemeRepository extends JpaRepository<Theme,Integer> {
	@Query("select c from Theme c where c.titre like :titre")
	public Page<Theme> themeByTitre(@Param("titre") String n, Pageable pageable);
	   
}
