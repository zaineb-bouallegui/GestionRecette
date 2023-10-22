package com.esprit.microservice.themesservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

	public Theme getThemeById(int id) {
		// Use the themeRepository or your data access logic to fetch the theme by ID
		Optional<Theme> optionalTheme = themeRepository.findById(id);

		if (optionalTheme.isPresent()) {
			return optionalTheme.get();
		} else {
			// Return null or handle the case where the theme with the specified ID is not found
			return null;
		}
	}

	public String exportThemesToCSV() {
		List<Theme> themes = themeRepository.findAll();

		if (themes.isEmpty()) {
			return ""; // or return an appropriate message
		} else {
			StringBuilder csvContent = new StringBuilder();
			csvContent.append("ID,Name,Description,CreateDate\n");

			for (Theme theme : themes) {
				csvContent.append(theme.getId()).append(",")
						.append(theme.getTitre()).append(",")
						.append(theme.getDescription()).append(",")
						.append(theme.getNombre_rct()).append("\n");
			}

			return csvContent.toString();
		}
	}
}
