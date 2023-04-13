package com.example.Projet;

import com.example.Projet.entity.Activite;
import com.example.Projet.entity.Utilisateur;
import com.example.Projet.service.ActiviteService;
import com.example.Projet.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication
public class ProjetApplication implements CommandLineRunner {

	@Autowired
	private UtilisateurService utilisateurService;

	@Autowired
	private ActiviteService activiteService;


	public static void main(String[] args) {
		SpringApplication.run(ProjetApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		utilisateurService.enregistreUtilisateur(new Utilisateur(null, "admin", "admin", "admin", "admin", "ADMIN"));
		utilisateurService.enregistreUtilisateur(new Utilisateur(null, "Mechin", "Amaury", "amaury@email.fr", "user", "USER"));
		utilisateurService.enregistreUtilisateur(new Utilisateur(null, "Bardel", "François", "francois@email.fr", "user", "USER"));
		activiteService.enregistrerActivite(new Activite(null, "Petanque", "Lancer des boules tout en buvant un ptit jaune, ça c'est ma France"));
	}
}
