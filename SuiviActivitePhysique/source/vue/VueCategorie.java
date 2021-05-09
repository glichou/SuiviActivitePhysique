package vue;

import java.util.ArrayList;
import controleur.ControleurCategorie;
import modele.Categorie;
import utilitaire.Clavier;

/**
 * Vue de la Catégorie.
 * @author lichou
 */
public class VueCategorie{
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
	private void ajouterUneCategorie() {
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
}
