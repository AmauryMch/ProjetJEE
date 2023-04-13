package com.example.Projet.service;

import com.example.Projet.entity.Activite;
import com.example.Projet.repositery.ActiviteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActiviteService {

    @Autowired
    private ActiviteRepository activiteRepository;

    public List<Activite> getAllActivites() {
        return activiteRepository.findAll();
    }

    public void enregistrerActivite(Activite activite) {
        activiteRepository.save(activite);
    }

    public Activite findActiviteByNom_activite(String nom) {
        return activiteRepository.findActiviteByNom_activite(nom);
    }

}
