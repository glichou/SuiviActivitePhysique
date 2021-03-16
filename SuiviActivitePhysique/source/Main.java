import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Scanner;

import controleur.ControleurApplication;
import modele.Categorie;
import modele.Favoris;
import modele.ModeleApplication;
import modele.Utilisateur;
import vue.VueApplication;

/**
 * Controleur de l'pplication de suivi d'activité physique.
 * @author lichou
 * @version 0.1
 */
public class Main {
	private static Scanner clavier;
	private static Utilisateur utilisateur;
	
	/**
	 * Point d'entrée de l'application de suivi d'activité physique.
	 * @param paramètres Les paramètres en entrée de l'application.
	 */
	public static void main(String[] parametres) {
		ModeleApplication modele = new ModeleApplication();
		VueApplication vue = new VueApplication();
		
		ControleurApplication controleur = new ControleurApplication(modele, vue);
		
		//Lancer l'application.
		vue.demarrer();
		
		//Truc à faire: méthode clone pour les objets, mettre en place les clone pour les acesseurs, faire des fonctions
		//pour l'affichage (découper les parties).
		
		//Décommenter pour bypasser la création du profil.
		//utilisateur = new Utilisateur("Jean", "Bon", 175, 63.40);
		
		//Favoris marathon = new Favoris("https://www.netflix.com/browse", Duration.of(24, ChronoUnit.HOURS), Categorie.COURSE, "Ne pas oublier le popcorn 🍿");
		//utilisateur.ajouterFavoris(marathon);
		
		/**
		System.out.println("Liste des favoris de " + utilisateur.getPrenom() + ":");
		ArrayList<Favoris> liste = utilisateur.getFavoris();
		for(int compteur = 0; compteur < liste.size(); compteur++) {
			System.out.println((compteur + 1) + "]\t" + liste.get(compteur).getLien());
		}
		System.out.print("\nPour plus d'information, veuillez entrer le numéro: ");
		int numero = clavier.nextInt();
		while(numero < 1 || numero > liste.size()) {
			System.out.print("Erreur! Ce numéro n'est pas dans la liste. Rééssayer: ");
			numero = clavier.nextInt();
		}
		System.out.println(liste.get(numero - 1));
		**/
	}
}
