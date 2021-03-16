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
	private static Utilisateur utilisateur;
	
	/**
	 * Point d'entrée de l'application de suivi d'activité physique.
	 * @param paramètres Les paramètres en entrée de l'application.
	 */
	public static void main(String[] parametres) {
		clavier = new Scanner(System.in);

		
		//Truc à faire: méthode clone pour les objets, mettre en place les clone pour les acesseurs, faire des fonctions
		//pour l'affichage (découper les parties).
		
		//Décommenter pour bypasser la création du profil.
		//utilisateur = new Utilisateur("Jean", "Bon", 175, 63.40);
		
		demarrerApplication();
		afficherAccueil();
		
		Favoris marathon = new Favoris("https://www.netflix.com/browse", Duration.of(24, ChronoUnit.HOURS), Categorie.COURSE, "Ne pas oublier le popcorn 🍿");
		utilisateur.ajouterFavoris(marathon);
		
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
	
	/**
	 * Afficher le logo et le slogan de l'application dans la console.
	 */
	public static void afficherLogo() {
		System.out.println("   _____       _       _    _____ _                 __   \n" + 
				"  / ___/__  __(_)   __(_)  / ___/(_)___ ___  ____  / /__ \n" + 
				"  \\__ \\/ / / / / | / / /   \\__ \\/ / __ `__ \\/ __ \\/ / _ \\\n" + 
				" ___/ / /_/ / /| |/ / /   ___/ / / / / / / / /_/ / /  __/\n" + 
				"/____/\\__,_/_/ |___/_/   /____/_/_/ /_/ /_/ .___/_/\\___/ \n" + 
				"                                         /_/             \n" +
				"\t\tSimplement là pour vous !\n");
	}
	
	
	public static void afficherAccueil() {
		System.out.println("\t[ACCUEIL]\n");
		System.out.println("1] Activité");
		System.out.println("2] Favoris");
	}
	
	public static void demarrerApplication() {
		afficherLogo();
		if(utilisateur != null) {
			System.out.println("\nHeureux de vous revoir " + utilisateur.getPrenom() + " !\n");
			System.out.println("Bienvenu dans votre application de suivi d'activité physique. Alors, prêt\npour une nouvelle séance d'entrainement ?\n");
		} else {
			System.out.println("\n\nDans cette application tu vas pouvoir suivre ton activité physique et ta\nprogession au fil du temps. Allez, hop hop hop, ne perdons pas de temps,\non va d'abord renseigner les informations de ton profil avant de commencer.\n");
			System.out.println("\t[PROFIL UTILISATEUR]\n");
			System.out.print("Prénom: ");
			String prenom = recupererNom();
			System.out.print("Nom: ");
			String nom = recupererNom();
			System.out.print("Taille (en cm): ");
			int taille = recupererTaille();
			System.out.print("Poids (en kg): ");
			double poids = recupererPoids();
			System.out.println();
			utilisateur = new Utilisateur(prenom, nom, taille, poids);
		}
	}
	
	public static String recupererNom() {
		String nom;
		boolean valide = false;
		do {
			nom = clavier.nextLine().trim();
			if(nom.length() < 1 ) {
				System.out.print("Fait un effort, il faut remplir le champ. Essayez à nouveau :  ");
			} else if (!nom.matches("\\p{L}*")) {
				System.out.print("Ça m'etonnerait qu'il y ait un nombre dans ton nom. Essayez à nouveau :  ");
			} else {
				valide = true;
			}
		} while(!valide);		
		return nom;
	}
	
	public static double recupererPoids() {
		double poids;
		boolean valide = false;
		do {
			while(!clavier.hasNextDouble()) {
				System.out.print("La valeur saisie n'est pas valide. Essayer de nouveau d'entrer votre poids en kilogrammes : ");
				clavier.next();
			}
			poids = clavier.nextDouble();
			if(poids > 10 && poids < 600) {
				valide = true;
			} else {
				System.out.print("Le poids saisit ne semble pas correct. Veuillez saisir une valeur valide :  ");
				clavier.next();
			}
		} while(!valide);		
		clavier.nextLine();
		return poids;
	}
	
	public static int recupererTaille() {
		int taille;
		boolean valide = false;
		do {
			while(!clavier.hasNextInt()) {
				System.out.print("La taille saisie n'est pas valide. Essayer de nouveau d'entrer votre taille en cm : ");
				clavier.next();
			}
			taille = clavier.nextInt();
			if(taille > 50 && taille < 300) {
				valide = true;
			} else {
				System.out.print("Le taille saisit ne semble pas correct. Veuillez saisir une valeur valide :  ");
			}
		} while(!valide);		
		clavier.nextLine();
		return taille;
	}
}
