package vue;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

import controleur.ControleurCategorie;
import modele.Activite;
import modele.Categorie;

public class VueCategorie{
	private ControleurCategorie controleur;	
	private Scanner clavier;
	
	/**
	 * Constructeur de la classe de VueCategorie.
	 */
	public VueCategorie() {
		//Créer un objet pour récupérer le texte saisit.
		clavier = new Scanner(System.in);
	}

	/**
	 * Définir le controleur en charge de cette vue.
	 * @param controleur Le controleur en charge de la vue.
	 */
	public void setControleur(ControleurCategorie controleur) {
		this.controleur = controleur;	
	}
	
	/**
	 * Récupérer la catégorie sélectionné par l'utilisateur.
	 * @return La catégorie sélectionné par l'utilisateur.
	 */
	public Categorie selectionnerCategorie() {
		Categorie categorie = null;
		
		boolean valide = false;
		int numeroSaisi;
		
		do {
			//Récupérer les catégories.
			ArrayList<Categorie> liste = this.controleur.recupererCategories();
			
			//Afficher et numéroter les catégories.
			int compteur = 1;
			System.out.println("Catégorie :");
			for(Categorie element : liste){
				System.out.println("  " + compteur + "] " + element.getLibelle());
				compteur++;
			}
			
			//Ajouter une option à la liste pour créer une nouvelle catégorie.
			System.out.println("  " +compteur + "] Nouveau...");
			
			//Afficher le message de demande.
			System.out.print("\nSélectionnez le numéro souhaité : ");
			
			//Vérifier que le valeur est au bon format.
			if(clavier.hasNextInt()) {
				numeroSaisi = clavier.nextInt();
				
				//Vérifier que le numéro est valide.
				if(numeroSaisi > 0 && numeroSaisi <= compteur) {
					
					if(numeroSaisi != compteur) {
						int index = numeroSaisi - 1;
						categorie = liste.get(index);
						valide = true;
					} else {
						ajouterUneCategorie();
					}
				} else {
					System.out.println("Option non valide.");
				}
			} else {
				System.out.println("La valeur saisie n'est pas valide.");
			}
		} while(!valide);
		return categorie;
	}
	
	/**
	 * Récupérer les informations pour ajouter une
	 * catégorie dans le profil de l'utilisateur.
	 */
	private void ajouterUneCategorie() {
		boolean valide = false;
		String chaine;

		System.out.print("Nom de la catégorie à ajouter : ");
		clavier.nextLine();
		do {
			//Récupérer la valeur saisie.
			chaine = clavier.nextLine().trim();
			
			//Vérifier la valeur saisie.
			if(chaine.length() < 1 ) {
				System.out.print("Veuillez saisir un nom de catégorie valide : ");
			} else {
				ArrayList<Categorie> liste = this.controleur.recupererCategories();
				
				//Vérifier si la catégorie existe déjà.
				boolean existe = false;
				for(Categorie element : liste){
					if(element.getLibelle().equals(chaine)) {
						existe = true;
					}
				}
		
				if(existe) {
					//Annuler la création si elle existe.
					System.out.println("La catégorie existe déjà.");
					valide = true;
				} else {
					//Ajouter la categorie.
					this.controleur.ajouterCategorie(chaine);
					valide = true;
				}
			}
		} while(!valide);
	}
}
