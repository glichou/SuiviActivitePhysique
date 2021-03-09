import java.time.Duration;
import java.time.temporal.ChronoUnit;

import modele.Categorie;
import modele.Entrainement;

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
		
		Entrainement unFavoris = new Entrainement("https://www.youtube.com/user/OutLawzFR", Duration.of(24, ChronoUnit.MINUTES), Categorie.COURSE, "");
		System.out.println(unFavoris);
	}
}
