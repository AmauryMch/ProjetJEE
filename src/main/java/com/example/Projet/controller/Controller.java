package com.example.Projet.controller;

import com.example.Projet.entity.Activite;
import com.example.Projet.entity.Utilisateur;
import com.example.Projet.service.ActiviteService;
import com.example.Projet.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@org.springframework.stereotype.Controller
public class Controller {

    @Autowired
    private ActiviteService activiteService;

    @Autowired
    private UtilisateurService utilisateurService;

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

    @GetMapping("/")
    public String home(HttpServletRequest request, Model model) {
        String s= request.getSession().getAttribute("email").toString();
        Utilisateur u = utilisateurService.findByEmail(s);
        model.addAttribute("user",u);
        return "home";
    }

    @GetMapping("/connexion")
    public String connexion() {
        return "formConnexion";
    }

    @GetMapping("/activite/add")
    public String showFormActivite() {
        return "formActivite";
    }

    @GetMapping("/inscription")
    public String showFormUtilisateur() {
        return "formInscription";
    }

    @PostMapping("/formActivite")
    public String addActivite(String nom_activite, String description_activite) {
        Activite activite = new Activite(null, nom_activite, description_activite);
        activiteService.enregistrerActivite(activite);
        return "redirect:/activites";
    }

    @PostMapping("/formInscription")
    public String addUtilisateur(String nom, String prenom, String email, String motDePasse) {
        Utilisateur utilisateur = new Utilisateur(null, nom, prenom, email, motDePasse);
        utilisateurService.enregistreUtilisateur(utilisateur);
        return "redirect:/utilisateurs";
    }

    @PostMapping("/formConnexion")
    public String connexion(String email, String motDePasse, Model model, HttpSession s) {
        HttpSession session = s;
        Utilisateur utilisateur = utilisateurService.findByEmail(email);
        if (utilisateur != null && utilisateur.getMot_de_passe().equals(motDePasse)) {
            model.addAttribute("User", utilisateur);
            session.setAttribute("email", utilisateur.getEmail());
            return "redirect:/";
        } else {
            model.addAttribute("erreur", "Email ou mot de passe invalide.");
            return "formConnexion";
        }
    }


}
