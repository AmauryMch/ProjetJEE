package com.example.Projet.repositery;

import com.example.Projet.entity.Programme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProgrammeReposetory extends JpaRepository<Programme, Long> {
}
