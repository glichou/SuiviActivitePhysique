package vue;

import java.util.Scanner;

import controleur.ControleurApplication;


public class VueApplication {
	private ControleurApplication controleur;	
	private Scanner clavier;
	
	/**
	 * Constructeur de la classe VueApplication.
	 */
	public VueApplication() {
		//Créer un objet pour récupérer le texte saisit.
		clavier = new Scanner(System.in);
	}
	
	/**
	 * Définir le controleur en charge de cette vue.
	 * @param controleur Le controleur en charge de la vue.
	 */
	public void setControleur(ControleurApplication controleur) {
		this.controleur = controleur;
	}
	
	/**
	 * Démarrer l'application.
	 */
	public void demarrer() {
		
		//Parceque... pourquoi pas ?! 
		afficherLogo();
		
		//Initialiser l'application.
		this.premierDemarrage();
		
		System.out.println("\t[ACCUEIL]\n");
		System.out.println("ACTIVITÉ");
		System.out.println("  1] Afficher les activités");
		System.out.println("  2] Afficher une activité");
		System.out.println("  3] Ajouter une activité");
		System.out.println("FAVORIS");
		System.out.println("  4] Afficher les favoris");
		System.out.println("  5] Afficher un favoris");
		System.out.println("  6] Ajouter un favoris");
		System.out.println("\nVeuillez choisir une option: ");
		
		afficherLogo();
		
		
	}
	
	
	/**
	 * Afficher l'assistant de configuration de l'application
	 * permettant de compléter le profil de l'utilisateur.
	 */
	public void premierDemarrage() {
		
		
		System.out.println("\n\nDans cette application vous allez pouvoir suivre votre activité physique et votre\nprogession au fil du temps. Allez, hop hop hop, ne perdons pas de temps,\ncommencons par renseigner les informations de votre profil avant de commencer.\n");
		System.out.println("\t[PROFIL UTILISATEUR]\n");
		System.out.print("Prénom: ");
		String prenom = recupererNom(clavier);
		System.out.print("Nom: ");
		String nom = recupererNom(clavier);
		System.out.print("Taille (en cm): ");
		int taille = recupererTaille(clavier);
		System.out.print("Poids (en kg): ");
		double poids = recupererPoids(clavier);
		System.out.println();
		
		this.controleur.creerCompte(prenom, nom, taille, poids);
	}
	
	/**
	 * Récupérer une valeur poids valide.
	 * @param clavier Le clavier en entrée.
	 * @return Le poids saisit par l'utilisateur.
	 */
	private static double recupererPoids(Scanner clavier) {
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
	
	/**
	 * Récupérer une valeur nom valide.
	 * @param clavier Le clavier en entrée.
	 * @return La taille saisit par l'utilisateur.
	 */
	private static String recupererNom(Scanner clavier) {
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
	
	/**
	 * Récupérer une valeur taille valide.
	 * @param clavier Le clavier en entrée.
	 * @return La taille saisit par l'utilisateur.
	 */
	private static int recupererTaille(Scanner clavier) {
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
	
}
