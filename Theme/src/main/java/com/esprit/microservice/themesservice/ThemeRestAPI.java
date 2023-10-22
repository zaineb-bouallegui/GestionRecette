package com.esprit.microservice.themesservice;

import org.keycloak.KeycloakPrincipal;
import org.keycloak.KeycloakSecurityContext;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/api/themes")
public class ThemeRestAPI {
	private String title = "Hello, I'm the theme Microservice";
	@Autowired
	private ThemeService themeService;
	@Autowired
	private CategorieService categorieService;

	@RequestMapping("/hello")
	public String sayHello() {
		System.out.println(title);
		return title;
	}

	@PostMapping
	@RequestMapping(value = "/admin")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Theme> createTheme(@RequestBody Theme theme, KeycloakAuthenticationToken auth) {
		KeycloakPrincipal<KeycloakSecurityContext> principal = (KeycloakPrincipal<KeycloakSecurityContext>) auth.getPrincipal();
		KeycloakSecurityContext context = principal.getKeycloakSecurityContext();
		boolean hasUserRole = context.getToken().getRealmAccess().isUserInRole("admin");

		if (hasUserRole) {
			return new ResponseEntity<>(themeService.addTheme(theme), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	@PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Theme> updateTheme(@PathVariable(value = "id") int id,
											 @RequestBody Theme theme,
											 @AuthenticationPrincipal KeycloakPrincipal<KeycloakSecurityContext> principal) {
		KeycloakSecurityContext context = principal.getKeycloakSecurityContext();
		boolean hasAdminRole = context.getToken().getRealmAccess().isUserInRole("admin");

		if (hasAdminRole) {
			return new ResponseEntity<>(themeService.updateTheme(id, theme), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	@DeleteMapping(value = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<String> deleteTheme(@PathVariable(value = "id") int id, KeycloakAuthenticationToken auth) {
		KeycloakPrincipal<KeycloakSecurityContext> principal = (KeycloakPrincipal<KeycloakSecurityContext>) auth.getPrincipal();
		KeycloakSecurityContext context = principal.getKeycloakSecurityContext();
		boolean hasUserRole = context.getToken().getRealmAccess().isUserInRole("admin");
		if (hasUserRole) {
			return new ResponseEntity<>(themeService.deleteTheme(id), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	@GetMapping(value = "/get", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<List<Theme>> getAllThemes(KeycloakAuthenticationToken auth) {
		KeycloakPrincipal<KeycloakSecurityContext> principal = (KeycloakPrincipal<KeycloakSecurityContext>) auth.getPrincipal();
		KeycloakSecurityContext context = principal.getKeycloakSecurityContext();
		boolean hasUserRole = context.getToken().getRealmAccess().isUserInRole("user");

		if (hasUserRole) {
			List<Theme> themes = themeService.getAllThemes();
			return new ResponseEntity<>(themes, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	@GetMapping(value = "/get/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Theme> getThemeById(@PathVariable("id") int id, KeycloakAuthenticationToken auth) {
		KeycloakPrincipal<KeycloakSecurityContext> principal = (KeycloakPrincipal<KeycloakSecurityContext>) auth.getPrincipal();
		KeycloakSecurityContext context = principal.getKeycloakSecurityContext();
		boolean hasUserRole = context.getToken().getRealmAccess().isUserInRole("user");

		if (hasUserRole) {
			Theme theme = themeService.getThemeById(id);
			if (theme != null) {
				return new ResponseEntity<>(theme, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	@GetMapping(value = "/export", produces = "text/csv")
	public ResponseEntity<String> exportThemesToCSV(KeycloakAuthenticationToken auth) {
		KeycloakPrincipal<KeycloakSecurityContext> principal = (KeycloakPrincipal<KeycloakSecurityContext>) auth.getPrincipal();
		KeycloakSecurityContext context = principal.getKeycloakSecurityContext();
		boolean hasUserRole = context.getToken().getRealmAccess().isUserInRole("admin");

		if (hasUserRole) {
			List<Theme> themes = themeService.getAllThemes();

			if (themes.isEmpty()) {
				return new ResponseEntity<>("No themes to export", HttpStatus.OK);
			} else {
				// Générer le contenu CSV à partir de la liste de thèmes
				StringBuilder csvContent = new StringBuilder();
				csvContent.append("ID,Titre,Description,Nombre de recettes\n");

				for (Theme theme : themes) {
					csvContent.append(theme.getId()).append(",")
							.append(theme.getTitre()).append(",")
							.append(theme.getDescription()).append(",")
							.append(theme.getNombre_rct()).append("\n"); // Add a line break here

				}

				// Renvoyer le contenu CSV en réponse
				HttpHeaders headers = new HttpHeaders();
				headers.add("Content-Disposition", "attachment; filename=themes.csv");
				return ResponseEntity.ok()
						.headers(headers)
						.body(csvContent.toString());
			}
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	/*************************************************************/


// Create a new Categorie
	@RequestMapping(value = "/categorie")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Categorie> createCategorie(@RequestBody Categorie categorie) {
		return new ResponseEntity<>(categorieService.addCategorie(categorie), HttpStatus.OK);
	}

	// Update an existing Categorie
	@PutMapping(value = "/categorie/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Categorie> updateCategorie(@PathVariable(value = "id") int id, @RequestBody Categorie categorie) {
		return new ResponseEntity<>(categorieService.updateCategorie(id, categorie), HttpStatus.OK);
	}

	// Delete a Categorie
	@DeleteMapping(value = "/categorie/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<String> deleteCategorie(@PathVariable(value = "id") int id) {
		return new ResponseEntity<>(categorieService.deleteCategorie(id), HttpStatus.OK);
	}

	// Get all Categories
	@GetMapping(value = "/categorie/get", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<List<Categorie>> getAllCategories() {
		List<Categorie> categories = categorieService.getAllCategories();
		return new ResponseEntity<>(categories, HttpStatus.OK);
	}

	// Get a specific Categorie by ID
	@GetMapping(value = "/categorie/get/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Categorie> getCategorieById(@PathVariable("id") int id) {
		Categorie categorie = categorieService.getCategorieById(id);
		if (categorie != null) {
			return new ResponseEntity<>(categorie, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

/********************************Fonctions avancées********************************************/

// Dans le contrôleur
@PostMapping("/categorie/{categorieId}/theme/{themeId}")
public ResponseEntity<String> addThemeToCategory(
		@PathVariable("categorieId") int categorieId,
		@PathVariable("themeId") int themeId
) {
	Categorie categorie = categorieService.getCategorieById(categorieId);
	Theme theme = themeService.getThemeById(themeId);

	if (categorie != null && theme != null) {
		// Assurez-vous que le thème appartient actuellement à une autre catégorie (s'il s'agit d'une relation de un à un)
		// Et mettez à jour la relation
		theme.setCategorie(categorie);
		themeService.updateTheme(themeId, theme);

		return new ResponseEntity<>("Thème ajouté à la catégorie.", HttpStatus.OK);
	} else {
		return new ResponseEntity<>("La catégorie ou le thème n'existe pas.", HttpStatus.NOT_FOUND);
	}
}





















}

