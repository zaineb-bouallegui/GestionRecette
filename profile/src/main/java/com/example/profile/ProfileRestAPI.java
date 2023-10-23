package com.example.profile;

import org.keycloak.KeycloakPrincipal;
import org.keycloak.KeycloakSecurityContext;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/profile")
public class ProfileRestAPI {
    private String title = "Hello, I'm the Profile Microservice";
    @Autowired
    ProfileService profileService;
    @RequestMapping("/hello")
    public String sayHello() {
        System.out.println(title);
        return title;
    }
    @GetMapping(value = "/get",produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Profile>> getAllProfiles(KeycloakAuthenticationToken auth) {
        KeycloakPrincipal<KeycloakSecurityContext> principal = (KeycloakPrincipal<KeycloakSecurityContext>) auth.getPrincipal();
        KeycloakSecurityContext context = principal.getKeycloakSecurityContext();
        boolean hasUserRole = context.getToken().getRealmAccess().isUserInRole("user");

        if (hasUserRole) {
            List<Profile> profiles = profileService.getAllProfiles();
            return new ResponseEntity<>(profiles, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }
    @PostMapping
    @RequestMapping(value = "/user")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Profile> createProfile(@RequestBody Profile profile, KeycloakAuthenticationToken auth) {
        KeycloakPrincipal<KeycloakSecurityContext> principal = (KeycloakPrincipal<KeycloakSecurityContext>) auth.getPrincipal();
        KeycloakSecurityContext context = principal.getKeycloakSecurityContext();
        boolean hasUserRole = context.getToken().getRealmAccess().isUserInRole("user");

        if (hasUserRole) {
            return new ResponseEntity<>(profileService.addProfile(profile), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }
    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Profile> updateProfile(@PathVariable(value = "id") int id,
                                                   @RequestBody Profile profile){
        return new ResponseEntity<>(profileService.updateProfile(id, profile), HttpStatus.OK);
    }
    @DeleteMapping(value = "/admin/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> deleteProfile(@PathVariable(value = "id") int id, KeycloakAuthenticationToken auth){
        KeycloakPrincipal<KeycloakSecurityContext> principal = (KeycloakPrincipal<KeycloakSecurityContext>) auth.getPrincipal();
        KeycloakSecurityContext context = principal.getKeycloakSecurityContext();
        boolean hasUserRole = context.getToken().getRealmAccess().isUserInRole("admin");
        if (hasUserRole) {
            return new ResponseEntity<>(profileService.deleteProfile(id), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }


    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Profile> getProfileById(@PathVariable(value = "id") int id, KeycloakAuthenticationToken auth) {
        KeycloakPrincipal<KeycloakSecurityContext> principal = (KeycloakPrincipal<KeycloakSecurityContext>) auth.getPrincipal();
        KeycloakSecurityContext context = principal.getKeycloakSecurityContext();
        boolean hasUserRole = context.getToken().getRealmAccess().isUserInRole("user");

        if (hasUserRole) {
            Optional<Profile> optionalProfile = profileService.getProfileById(id);
            if (optionalProfile.isPresent()) {
                Profile profile = optionalProfile.get();
                return new ResponseEntity<>(profile, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @PatchMapping(value = "/{id}/privacy")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Profile> updateProfilePrivacyStatus(@PathVariable(value = "id") int id, @RequestBody Map<String, Boolean> privacyUpdate, KeycloakAuthenticationToken auth) {
        boolean newPrivacyStatus = privacyUpdate.get("isPublic");

        // Vérifiez si l'utilisateur a le rôle "profile_manager"
        if (isUserInRole(auth, "user")) {
            // L'utilisateur a le rôle nécessaire pour effectuer cette opération.
            Profile updatedProfile = profileService.updatePrivacyStatus(id, newPrivacyStatus);
            if (updatedProfile != null) {
                return new ResponseEntity<>(updatedProfile, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } else {
            // L'utilisateur n'a pas le rôle requis, renvoyez une réponse non autorisée.
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    private boolean isUserInRole(KeycloakAuthenticationToken auth, String roleName) {
        KeycloakPrincipal<KeycloakSecurityContext> principal = (KeycloakPrincipal<KeycloakSecurityContext>) auth.getPrincipal();
        KeycloakSecurityContext context = principal.getKeycloakSecurityContext();
        return context.getToken().getRealmAccess().isUserInRole(roleName);
    }


    @PutMapping(value = "/activate/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Profile> activateProfile(@PathVariable(value = "id") int id) {
        return new ResponseEntity<>(profileService.activateProfile(id), HttpStatus.OK);
    }


    @PutMapping(value = "/deactivate/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Profile> deactivateProfile(@PathVariable(value = "id") int id) {
        return new ResponseEntity<>(profileService.deactivateProfile(id), HttpStatus.OK);
    }


}