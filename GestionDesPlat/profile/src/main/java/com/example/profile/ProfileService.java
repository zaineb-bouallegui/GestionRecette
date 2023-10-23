package com.example.profile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfileService {

    @Autowired
    private ProfileRepository profileRepository;

    public Profile addProfile(Profile profile){return profileRepository.save(profile);}
    public Profile updateProfile(int id, Profile newProfile) {
        if (profileRepository.findById(id).isPresent()) {
            Profile existingProfile = profileRepository.findById(id).get();
            existingProfile.setNom(newProfile.getNom());
            existingProfile.setPrenom(newProfile.getPrenom());
            existingProfile.setImg(newProfile.getImg());

            return profileRepository.save(existingProfile);
        } else
            return null;
    }
    public String deleteProfile(int id) {
        if (profileRepository.findById(id).isPresent()) {
            profileRepository.deleteById(id);
            return "profile supprimé";
        } else
            return "profile non supprimé";
    }
    public List<Profile> getAllProfiles() {
        return profileRepository.findAll();
    }

    public Optional<Profile> getProfileById(int id) {
        return profileRepository.findById(id);
    }

    public Profile updatePrivacyStatus(int id, boolean isPublic) {
        Optional<Profile> optionalProfile = profileRepository.findById(id);

        if (optionalProfile.isPresent()) {
            Profile profile = optionalProfile.get();
            profile.setPublic(isPublic);
            return profileRepository.save(profile);
        } else {
            return null; // Gérer le cas où le profil n'existe pas
        }
    }

    public Profile activateProfile(int id) {
        if (profileRepository.findById(id).isPresent()) {
            Profile existingProfile = profileRepository.findById(id).get();
            existingProfile.setActive(true);
            return profileRepository.save(existingProfile);
        } else {
            return null;
        }
    }
    public Profile deactivateProfile(int id) {
        if (profileRepository.findById(id).isPresent()) {
            Profile existingProfile = profileRepository.findById(id).get();
            existingProfile.setActive(false);
            return profileRepository.save(existingProfile);
        } else {
            return null;
        }
    }
}