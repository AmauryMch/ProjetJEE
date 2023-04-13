package com.example.Projet.controller;

import com.example.Projet.entity.Activite;
import com.example.Projet.entity.Programme;
import com.example.Projet.entity.Utilisateur;
import com.example.Projet.repositery.ProgrammeReposetory;
import com.example.Projet.service.ActiviteService;
import com.example.Projet.service.ProgrammeService;
import com.example.Projet.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@org.springframework.stereotype.Controller
public class Controller {

    @Autowired
    private ActiviteService activiteService;

    @Autowired
    private UtilisateurService utilisateurService;

    @Autowired
    private ProgrammeService programmeService;

    @GetMapping("/utilisateurs")
    public String afficherUtilisateurs(Model model) {
        List<Utilisateur> utilisateur = utilisateurService.getAllUtilisateurs();
        model.addAttribute("utilisateur", utilisateur);
        return "utilisateurs";
    }

    @GetMapping("/")
    public String home(HttpServletRequest request, Model model, HttpSession s) {
        if(request.getSession().equals(null)) {
            String emailRequest = request.getSession().getAttribute("email").toString();

            Utilisateur u = utilisateurService.findByEmail(emailRequest);
            model.addAttribute("user", u);

            List<Programme> p = u.getProgrammes();
            model.addAttribute("programmes", p);

        }
        List<Activite> activite = activiteService.getAllActivites();
        model.addAttribute("activite", activite);
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
    public String addActivite(String nom, String description_activite) {
        Activite activite = new Activite(null, nom, description_activite, null);
        activiteService.enregistrerActivite(activite);
        return "redirect:/";
    }

    @PostMapping("/formInscription")
    public String addUtilisateur(String nom, String prenom, String email, String motDePasse) {
        List<Programme>l =new ArrayList<Programme>();
        Utilisateur utilisateur = new Utilisateur(null, nom, prenom, email, motDePasse, "USER", l);
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
            session.setAttribute("nom", utilisateur.getNom());
            session.setAttribute("prenom", utilisateur.getPrenom());
            session.setAttribute("id", utilisateur.getId_utilisateur());
            session.setAttribute("role", utilisateur.getRole());
            session.setAttribute("programmes", utilisateur.getProgrammes());
            return "redirect:/";
        } else {
            model.addAttribute("erreur", "Email ou mot de passe invalide.");
            return "formConnexion";
        }
    }
    @GetMapping("/profil")
    public String profil(Model model, HttpSession s){
        String email=s.getAttribute("email").toString();
        Utilisateur u=utilisateurService.findByEmail(email);
        List<Programme> p = u.getProgrammes();
        model.addAttribute("programmes", p);
        return "profil";
    }

    @PostMapping("/add")
    public String ajoutProgramme(String nom, Model model, HttpSession s){

        System.out.println(activiteService.findByNom(nom));
        return "redirect:/profil";
    }

    @PostMapping("/newProgramme")
    public String NewProgramme(String nom, Model model, HttpSession s){
        String email=s.getAttribute("email").toString();
        Utilisateur u=utilisateurService.findByEmail(email);
        Programme p=new Programme(null, nom,u, new ArrayList<Activite>());
        programmeService.enregistreProgramme(p);
        List<Programme> pl = u.getProgrammes();
        pl.add(p);
        u.setProgrammes(pl);
        utilisateurService.enregistreUtilisateur(u);
        return "redirect:/profil";
    }
}
