package com.example.Projet.repositery;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.Projet.entity.Role;


public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByNom(String nom);
}
