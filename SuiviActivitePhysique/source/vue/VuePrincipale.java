package vue;

import controleur.ControleurPrincipal;
import utilitaire.Clavier;

/**
 * Vue principale de l'application.
 * @author Grégoire LICHOU
 * @author Quentin COUSTURIAN
 */
public class VuePrincipale {
	private ControleurPrincipal controleur;	
	private Clavier clavier;
	
	/**
	 * Constructeur de la classe VueApplication.
	 */
	public VuePrincipale() {
		//Créer un objet pour récupérer le texte saisit.
		clavier = new Clavier(System.in);
	}
	
	/**
	 * Définir le controleur en charge de cette vue.
	 * @param controleur Le controleur en charge de la vue.
	 */
	public void setControleur(ControleurPrincipal controleur) {
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
		
		int numeroSaisi;
		
		//Afficher le menu tant que l'utilisateur ne souhaite pas sortir.
		do {
			System.out.println("\t[ACCUEIL]\n");
			System.out.println("1] Activité");
			System.out.println("2] Favoris");
			System.out.println("3] Catégories");
			System.out.println("4] QUITTER");
			System.out.print("\nVeuillez choisir une option: ");
			
			//Récupérer la valeur saisie par l'utilisateur.
			numeroSaisi = clavier.recupererNombre(1, 4);
			System.out.println();
			
			// Executer l'action demandé par l'utilisateur.
			switch(numeroSaisi) {
				case 1:
					controleur.afficherMenuActivite();
					break;
				case 2:
					controleur.afficherMenuFavori();
					break;
				case 3:
					controleur.afficherMenuCategorie();
					break;
			}
		} while(numeroSaisi != 4);
		
		//Parceque... pourquoi pas ?! 
		afficherLogo();
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

	
	/**
	 * Afficher l'assistant de configuration de l'application
	 * permettant de compléter le profil de l'utilisateur.
	 */
	public void premierDemarrage() {
		if(!this.controleur.utilisateurConnecte()) {
			System.out.println("\n\nDans cette application vous allez pouvoir suivre votre activité physique et votre\nprogession au fil du temps. Allez, hop hop hop, ne perdons pas de temps,\ncommencons par renseigner les informations de votre profil avant de commencer.\n");
			System.out.println("\t[PROFIL UTILISATEUR]\n");
			System.out.print("Prénom: ");
			String prenom = clavier.recupererTexteCourt(false, true);
			System.out.print("Nom: ");
			String nom = clavier.recupererTexteCourt(false, true);
			System.out.print("Taille (en cm): ");
			int taille = clavier.recupererNombre(50, 300);
			System.out.print("Poids (en kg): ");
			double poids = clavier.recupererNombreDecimal(10.0, 600.0);
			System.out.println();
			this.controleur.creerCompte(prenom, nom, taille, poids);
		} else {
			System.out.println("Bienvenue " + this.controleur.recupererPrenomUtilisateur() + " " + this.controleur.recupererNomUtilisateur() + " !\n");
		}
	}
}
