package controleur;

import java.util.ArrayList;

import modele.Activite;
import modele.Categorie;
import modele.ModeleApplication;
import vue.VueActivite;
import vue.VueCategorie;

/**
 * Classe controleur.
 * @author lichou
 */
public class ControleurCategorie {
	private ModeleApplication modele;
	private VueCategorie vue;
	
	/**
	 * Controleur de la classe ControleurCategorie
	 * @param modele Le modèle du controleur.
	 * @param vue La vue du controleur.
	 */
	public ControleurCategorie(ModeleApplication modele, VueCategorie vue) {
		this.modele = modele;
		this.vue = vue;
		this.vue.setControleur(this);
	}

	/**
	 * Récupérer l'ensemble des catégories de l'utilisateur.
	 * @return Les catégories de l'activités.
	 */
	public ArrayList<Categorie> recupererCategories() {
		return this.modele.getUtilisateur().getCategories();
	}
	
	/**
	 * Ajouter une catégorie dans le profil de l'utilisateur.
	 * @param libelle Le libelle de la catégorie.
	 */
	public void ajouterCategorie(String libelle) {
		Categorie categorie = new Categorie(libelle);
		this.modele.getUtilisateur().addCategorie(categorie);
	}	
}
