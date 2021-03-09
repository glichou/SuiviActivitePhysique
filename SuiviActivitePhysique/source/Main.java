import java.time.Duration;
import java.time.temporal.ChronoUnit;

import modele.Categorie;
import modele.Entrainement;
import modele.Utilisateur;

/**
 * Controleur de l'pplication de suivi d'activité physique.
 * @author lichou
 * @version 0.1
 */
public class Main {
	
	/**
	 * Point d'entrée de l'application de suivi d'activité physique.
	 * @param paramètres Les paramètres en entrée de l'application.
	 */
	public static void main(String[] parametres) {
		System.out.println("Ouais ouais");
		
		Utilisateur jean = new Utilisateur("Jean", "Bon");
		
		Entrainement marathon = new Entrainement("https://www.netflix.com/browse", Duration.of(24, ChronoUnit.HOURS), Categorie.COURSE, "Ne pas oublier le popcorn 🍿");
		
		jean.ajouterFavoris(marathon);
		System.out.println(marathon);
	}
}
