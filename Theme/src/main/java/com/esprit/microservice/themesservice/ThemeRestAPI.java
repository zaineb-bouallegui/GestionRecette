package com.esprit.microservice.themesservice;

import org.keycloak.KeycloakPrincipal;
import org.keycloak.KeycloakSecurityContext;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping(value = "/api/themes")
public class ThemeRestAPI {
	private String title = "Hello, I'm the theme Microservice";
	@Autowired
	private ThemeService themeService;
	@RequestMapping("/hello")
	public String sayHello() {
		System.out.println(title);
		return title;
	}
	@PostMapping
	@RequestMapping(value = "/user")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Theme> createTheme(@RequestBody Theme theme, KeycloakAuthenticationToken auth) {
		KeycloakPrincipal<KeycloakSecurityContext> principal = (KeycloakPrincipal<KeycloakSecurityContext>) auth.getPrincipal();
		KeycloakSecurityContext context = principal.getKeycloakSecurityContext();
		boolean hasUserRole = context.getToken().getRealmAccess().isUserInRole("user");

		if (hasUserRole) {
			return new ResponseEntity<>(themeService.addTheme(theme), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}
	@PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Theme> updateTheme(@PathVariable(value = "id") int id,
												   @RequestBody Theme theme){
		return new ResponseEntity<>(themeService.updateTheme(id, theme), HttpStatus.OK);
	}
	@DeleteMapping(value = "/admin/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<String> deleteTheme(@PathVariable(value = "id") int id, KeycloakAuthenticationToken auth){
		KeycloakPrincipal<KeycloakSecurityContext> principal = (KeycloakPrincipal<KeycloakSecurityContext>) auth.getPrincipal();
		KeycloakSecurityContext context = principal.getKeycloakSecurityContext();
		boolean hasUserRole = context.getToken().getRealmAccess().isUserInRole("admin");
		if (hasUserRole) {
			return new ResponseEntity<>(themeService.deleteTheme(id), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}
	@GetMapping(value = "/get",produces = MediaType.APPLICATION_JSON_VALUE)
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

	@GetMapping(value = "/getById/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Optional<Theme>> retrieveAllThemes(@PathVariable("id") int id, KeycloakAuthenticationToken auth) {
		KeycloakPrincipal<KeycloakSecurityContext> principal = (KeycloakPrincipal<KeycloakSecurityContext>) auth.getPrincipal();
		KeycloakSecurityContext context = principal.getKeycloakSecurityContext();
		boolean hasUserRole = context.getToken().getRealmAccess().isUserInRole("user");

		if (hasUserRole) {
			Optional<Theme> themes = themeService.getById(id);
			return new ResponseEntity<>(themes, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}


}
