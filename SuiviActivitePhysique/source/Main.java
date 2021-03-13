import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Scanner;

import modele.Categorie;
import modele.Favoris;
import modele.Utilisateur;

/**
 * Controleur de l'pplication de suivi d'activité physique.
 * @author lichou
 * @version 0.1
 */
public class Main {
	private static Scanner clavier;
	
	/**
	 * Point d'entrée de l'application de suivi d'activité physique.
	 * @param paramètres Les paramètres en entrée de l'application.
	 */
	public static void main(String[] parametres) {
		clavier = new Scanner(System.in);

		
		//Truc à faire: méthode clone pour les objets, mettre en place les clone pour les acesseurs, faire des fonctions
		//pour l'affichage (découper les parties).
		System.out.println("Ouais ouais");
		
		Utilisateur jean = new Utilisateur("Jean", "Bon");
		
		Favoris marathon = new Favoris("https://www.netflix.com/browse", Duration.of(24, ChronoUnit.HOURS), Categorie.COURSE, "Ne pas oublier le popcorn 🍿");
		jean.ajouterFavoris(marathon);
		
		System.out.println("Liste des favoris de " + jean.getPrenom() + ":");
		ArrayList<Favoris> liste = jean.getFavoris();
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
	}
}
