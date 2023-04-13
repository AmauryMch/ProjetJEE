package com.example.Projet.service;

import com.example.Projet.entity.Programme;
import com.example.Projet.entity.Utilisateur;
import com.example.Projet.repositery.ProgrammeReposetory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProgrammeService {

    @Autowired
    private ProgrammeReposetory programmeReposetory;

    public void enregistreProgramme(Programme p) {
        programmeReposetory.save(p);
    }
}
