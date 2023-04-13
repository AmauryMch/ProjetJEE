package com.example.Projet.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "activite")
public class Activite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_activite;

    private String nom;

    private String description_activite;

    @ManyToOne
    @JoinColumn(name = "id_programme")
    private Programme programme;
}

