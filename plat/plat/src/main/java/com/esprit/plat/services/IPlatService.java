package com.esprit.plat.services;

import com.esprit.plat.entities.Plat;

import java.util.List;

public interface IPlatService {
    List<Plat> addPlats(List<Plat> plats);

    Plat updatePlat(Long id, Plat plat);

    String deletePlat(Long id);

    Plat getPlatById(Long id);

    List<Plat> getAllPlats();
}
