package com.example.Projet.repositery;

import com.example.Projet.entity.Notation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotationReposetory extends JpaRepository<Notation, Long> {
}
