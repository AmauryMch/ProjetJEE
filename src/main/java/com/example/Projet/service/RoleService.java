package com.example.Projet.service;

import com.example.Projet.entity.Utilisateur;
import com.example.Projet.repositery.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.Projet.entity.Role;

@Service
public class RoleService {

    @Autowired
    RoleRepository roleRepository;

    public void initRoles(){
        createRoleIfNotFound("USER");
        createRoleIfNotFound("ADMIN");

    }

    public void createRoleIfNotFound(String nom) {
        Role role = roleRepository.findByNom(nom);
        if (role == null) {
            role = new Role();
            role.setNom(nom);
            roleRepository.save(role);
        }
    }

    public Role findByNom(String nom) {
        return roleRepository.findByNom(nom);
    }
}
