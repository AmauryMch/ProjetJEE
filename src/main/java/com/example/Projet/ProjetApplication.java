package com.example.Projet;

import com.example.Projet.entity.Activite;
import com.example.Projet.entity.Programme;
import com.example.Projet.entity.Utilisateur;
import com.example.Projet.service.ActiviteService;
import com.example.Projet.service.ProgrammeService;
import com.example.Projet.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class ProjetApplication implements CommandLineRunner {

	@Autowired
	private UtilisateurService utilisateurService;

	@Autowired
	private ActiviteService activiteService;

	@Autowired
	private ProgrammeService programmeService;


	public static void main(String[] args) {
		SpringApplication.run(ProjetApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Utilisateur u=new Utilisateur(null, "User", "user", "user", "user", "USER",new ArrayList<Programme>());
		utilisateurService.enregistreUtilisateur(new Utilisateur(null, "admin", "admin", "admin", "admin", "ADMIN", new ArrayList<Programme>()));
		utilisateurService.enregistreUtilisateur(new Utilisateur(null, "Mechin", "Amaury", "amaury@email.fr", "user", "USER",new ArrayList<Programme>()));
		utilisateurService.enregistreUtilisateur(new Utilisateur(null, "Bardel", "François", "francois@email.fr", "user", "USER",new ArrayList<Programme>()));

		activiteService.enregistrerActivite(new Activite(null, "Petanque", "Lancer des boules tout en buvant un ptit jaune", null, null));
		activiteService.enregistrerActivite(new Activite(null, "Escalade", "Gare a toi Inox, je serais plus rapide", null, null));
		activiteService.enregistrerActivite(new Activite(null, "Ark", "Dino dino", null, null));
		activiteService.enregistrerActivite(new Activite(null, "Beyblade", "Devenir le meilleur blader", null, null));

		activiteService.enregistrerActivite(new Activite(null, "Flop", "Grim vous enseignera lart de l'humour par le flop", null, null));
		activiteService.enregistrerActivite(new Activite(null, "BedWar", "Pour defoncer les 3 joueurs restant sur OneCube", null, null));
		activiteService.enregistrerActivite(new Activite(null, "Ark saison 2", "Dino dino, mais dans le desert cette fois ci", null, null));
		activiteService.enregistrerActivite(new Activite(null, "Ark saison 3: Minecraft", "Dino dino mais cubique", null, null));

		utilisateurService.enregistreUtilisateur(u);

		Programme p=new Programme(null, "Sport",u, new ArrayList<Activite>());
		Programme p2=new Programme(null, "Geek puant",u, new ArrayList<Activite>());
		programmeService.enregistreProgramme(p);
		programmeService.enregistreProgramme(p2);
		List<Programme> pl = u.getProgrammes();
		pl.add(p);
		pl.add(p2);
		u.setProgrammes(pl);
		utilisateurService.enregistreUtilisateur(u);


	}
}
