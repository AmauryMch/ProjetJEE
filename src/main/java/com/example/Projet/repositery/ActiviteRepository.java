package com.example.Projet.repositery;

import com.example.Projet.entity.Activite;
import com.example.Projet.entity.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActiviteRepository extends JpaRepository<Activite, Long> {
    Activite findByNom(String nom);
}
