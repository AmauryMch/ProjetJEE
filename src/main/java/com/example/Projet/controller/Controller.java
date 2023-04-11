package com.example.Projet.controller;

import com.example.Projet.entity.Activite;
import com.example.Projet.entity.Utilisateur;
import com.example.Projet.service.ActiviteService;
import com.example.Projet.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@org.springframework.stereotype.Controller
public class Controller {

    @Autowired
    private ActiviteService activiteService;

    @Autowired
    private UtilisateurService utilisateurService;

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/activites")
    public String afficherActivites(Model model) {
        List<Activite> activite = activiteService.getAllActivites();
        model.addAttribute("activite", activite);
        return "activites";
    }

    @GetMapping("/utilisateurs")
    public String afficherUtilisateurs(Model model) {
        List<Utilisateur> utilisateur = utilisateurService.getAllUtilisateurs();
        model.addAttribute("utilisateur", utilisateur);
        return "utilisateurs";
    }

    @GetMapping("/connexion")
    public String connexion() {
        return "formConnexion";
    }

    @GetMapping("/activite/add")
    public String showFormActivite() {
        return "formActivite";
    }

    @GetMapping("/utilisateur/add")
    public String showFormUtilisateur() {
        return "formUtilisateur";
    }

    @PostMapping("/formActivite")
    public String addActivite(String nom_activite, String description_activite) {
        Activite activite = new Activite(null, nom_activite, description_activite);
        activiteService.enregistrerActivite(activite);
        return "redirect:/activites";
    }

    @PostMapping("/formUtilisateur")
    public String addUtilisateur(String nom, String prenom, String email, String motDePasse) {
        Utilisateur utilisateur = new Utilisateur(null, nom, prenom, email, motDePasse);
        utilisateurService.enregistreUtilisateur(utilisateur);
        return "redirect:/utilisateurs";
    }

    @PostMapping("/formConnexion")
    public String connexion(String email, String motDePasse, Model model) {
        Utilisateur utilisateur = utilisateurService.findByEmail(email);
        if (utilisateur != null && utilisateur.getMot_de_passe().equals(motDePasse)) {
            return "redirect:/activites";
        } else {
            model.addAttribute("erreur", "Email ou mot de passe invalide.");
            return "formconnexion";
        }
    }


}
