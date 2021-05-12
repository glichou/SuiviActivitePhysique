 package vue;

import java.util.ArrayList;
import controleur.ControleurCategorie;
import modele.Categorie;
import utilitaire.Clavier;

/**
 * Vue de la Catégorie.
 * @author lichou
 */
public class VueCategorie {
	private ControleurCategorie controleur;	
	private Clavier clavier;
	
	/**
	 * Constructeur de la classe de VueCategorie.
	 */
	public VueCategorie() {
		//Créer un objet pour récupérer le texte saisit.
		clavier = new Clavier(System.in);
	}

	/**
	 * Définir le controleur en charge de cette vue.
	 * @param controleur Le controleur en charge de la vue.
	 */
	public void setControleur(ControleurCategorie controleur) {
		this.controleur = controleur;	
	}
	
	/**
	 * Afficher le menu principal de sélection des options
	 * pour les catégories.
	 */
	public void afficherMenu() {
		int numeroSaisi;
		do {
			System.out.println("\t[CATÉGORIE]\n");
			System.out.println("1] Afficher les catégories");
			System.out.println("2] Ajouter une catégorie");
			System.out.println("3] Supprimer une catégorie");
			System.out.println("4] Revenir à l'accueil");
			System.out.print("\nSaisir l'option voulue: ");
			
			numeroSaisi = clavier.recupererNombre(1, 4);
			System.out.println();

			//Executer l'action demandé par l'utilisateur.
			switch(numeroSaisi) {
				case 1:
					this.afficherLesCategories();
					break;
				case 2:
					this.ajouterUneCategorie();
					break;
				case 3:
					this.supprimerUneCategorie();
					break;
			}
		} while(numeroSaisi != 4);
	}

	/**
	 * Afficher les catégories du profil de l'utilisateur.
	 */
	public void afficherLesCategories() {
		ArrayList<Categorie> liste = this.controleur.recupererCategories();
		
		if(liste.size() > 0) {
			System.out.println("Voici la liste de vos catégories :");
			for(int i = 0; i < liste.size(); i++) {
				System.out.println((i + 1) + "] " + liste.get(i).toSmallString());
			}
		} else {
			System.out.println("→ Vous n'avez encore aucune catégorie pour le moment !");
		}
		System.out.println();
	}
	
	/**
	 * Récupérer la catégorie sélectionné par l'utilisateur.
	 * @return La catégorie sélectionné par l'utilisateur.
	 */
	public Categorie selectionnerCategorie() {
		int index;
		
		do {
			System.out.println("Catégorie :");
			
			//Récupérer les catégories.
			ArrayList<Categorie> liste = this.controleur.recupererCategories();
			
			//Lister les catégories existantes et l'option permettant l'ajout d'une nouvelle.
			for(index = 0; index < liste.size(); index++){
				System.out.println("  " + (index + 1) + "] " + liste.get(index).toSmallString());
			}
			System.out.println("  " + (index + 1) + "] Nouveau…");
			
			//Récupérer l'option selectionné par l'utilisateur.
			System.out.print("\nSélectionnez le numéro souhaité : ");
			int numero = clavier.recupererNombre(1, (index + 1));
			
			if(numero == (index + 1)) {
				ajouterUneCategorie();
			} else {
				return liste.get(numero - 1);
			}
		} while(true);
	}
	
	/**
	 * Récupérer les informations pour ajouter une
	 * catégorie dans le profil de l'utilisateur.
	 */
	public void ajouterUneCategorie() {
		boolean valide = false;
		String chaine;
		
		System.out.print("Nom de la catégorie à ajouter : ");
		do {
			chaine  = clavier.recupererTexteCourt(false, true);

			Categorie categorie = new Categorie(chaine);			
			if(this.controleur.ajouterCategorie(categorie)) {
				valide = true;
			} else {
				System.out.print("La catégorie existe déjà ! Nom de la catégorie à ajouter : ");
			}
		} while(!valide);
	}
	
	/**
	 * Supprimer une catégorie dans le profil de l'utilisateur.
	 */
	public void supprimerUneCategorie() {		
		int index = 0;
		if(this.controleur.recupererNbCategories() > 0) {
			
			System.out.print("Veuillez saisir l'identifiant de la catégorie : ");
			index = clavier.recupererNombre(1, this.controleur.recupererNbCategories());
			
			try {
				this.controleur.supprimerCategorie(index - 1);
				System.out.println("La catégorie sélectionnée a bien été supprimée !");
			} catch (Exception e) {
				System.out.println("La catégorie selectionnée n'existe pas !");
			}
		} else {
			System.out.println("→ Vous n'avez encore aucune catégorie pour le moment !");
		}
		System.out.println();
	}
} 
