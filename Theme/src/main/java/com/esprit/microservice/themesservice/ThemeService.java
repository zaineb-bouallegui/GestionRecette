package com.esprit.microservice.themesservice;

import jdk.nashorn.internal.ir.Optimistic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class ThemeService {

	@Autowired
	private ThemeRepository themeRepository;

	public Theme addTheme(Theme theme) {
		return themeRepository.save(theme);
	}
	public Theme updateTheme(int id, Theme newTheme) {
		if (themeRepository.findById(id).isPresent()) {
			Theme existingTheme = themeRepository.findById(id).get();
			existingTheme.setTitre(newTheme.getTitre());
			existingTheme.setDescription(newTheme.getDescription());
			existingTheme.setNombre_rct(newTheme.getNombre_rct());

			return themeRepository.save(existingTheme);
		} else
			return null;
	}
	public String deleteTheme(int id) {
		if (themeRepository.findById(id).isPresent()) {
			themeRepository.deleteById(id);
			return "theme supprimée";
		} else
			return "theme non supprimée";
	}
	public List<Theme> getAllThemes() {
		return themeRepository.findAll();
	}

	public Optional<Theme> getById(Integer id){
		return themeRepository.findById(id);
	}
}
