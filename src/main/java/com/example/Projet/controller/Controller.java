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
    public String home(Model model, HttpSession s) {
        if(!s.equals(null)) {
            String email = (String) s.getAttribute("email");
            if(email != null) {
                Utilisateur u = utilisateurService.findByEmail(email);
                model.addAttribute("user", u);

                List<Programme> p = u.getProgrammes();
                model.addAttribute("ListeProgrammes", p);
            }
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
    public String ajoutProgramme(String nom, String choixProg, Model model, HttpSession s, HttpServletRequest request){
        String email=s.getAttribute("email").toString();
        Utilisateur u=utilisateurService.findByEmail(email);
        List<Programme> lp=u.getProgrammes();
        activiteService.findByNom(nom);
        for(int temp=0; temp< lp.size();temp++){
            if(lp.get(temp).getNom().equals(choixProg)){
                Programme p=lp.get(temp);
                List<Activite> la=p.getActivites();
                la.add(activiteService.findByNom(nom));
                p.setActivites(la);
                programmeService.enregistreProgramme(p);
            }
        }
        System.out.println(activiteService.findByNom(choixProg));
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

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "/formConnexion";
    }
}
