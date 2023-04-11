package com.example.Projet.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "utilisateur")
public class Utilisateur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_utilisateur;

    private String nom;

    private String prenom;

    private String email;

    private String mot_de_passe;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "utilisateur_role",joinColumns = @JoinColumn(name = "id_utilisateur", referencedColumnName = "id_utilisateur"),
            inverseJoinColumns = @JoinColumn(name = "id_role", referencedColumnName = "id_role"))
    private List<Role> roles = new ArrayList<>();

}
