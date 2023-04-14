package com.example.Projet.service;

import com.example.Projet.entity.Notation;
import com.example.Projet.entity.Programme;
import com.example.Projet.repositery.NotationReposetory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotationService {

    @Autowired
    NotationReposetory notationReposetory;

    public void enregistreNotation(Notation n) {
        notationReposetory.save(n);
    }

}
