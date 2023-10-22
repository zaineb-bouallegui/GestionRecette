package com.esprit.plat.services;

import com.esprit.plat.entities.Plat;

import java.util.List;
import java.util.Map;

public interface IPlatService {
    List<Plat> addPlats(List<Plat> plats);

    Plat updatePlat(Long id, Plat plat);

    String deletePlat(Long id);

    Plat getPlatByIdWithRecetteDetails(Long id);

    List<Map<String, Object>> getAllPlatsWithRecetteDetails();
}
