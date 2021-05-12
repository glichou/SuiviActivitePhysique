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
	 * Récupérer l'ensemble des catégories de l'utilisateur.
	 * @param index Les catégories de l'activités.
	 * @return La catégorie à l'index demandé.
	 * @throws Exception Lever une exception s'il n'y a pas de catégorie à cet index.
	 */
	public Categorie recupererCategorie(int index) throws Exception {
		return this.modele.getUtilisateur().recupererCategorie(index);
	}
	
	/**
	 * Ajouter une catégorie dans le profil de l'utilisateur.
	 * @param libelle Le libelle de la catégorie.
	 */
	public boolean ajouterCategorie(Categorie categorie) {
		return this.modele.getUtilisateur().ajouterCategorie(categorie);
	}

	/**
	 * Récupérer le nombre de catégories de l'utilisateur.
	 * @return Le nombre de catégories.
	 */
	public int recupererNbCategories() {
		return this.modele.getUtilisateur().getNbCategories();
	}

	/**
	 * Supprimer une catégorie de l'utilisateur à un index.
	 * @param index L'index de la catégorie à supprimer.
	 * @throws Exception Lever une exception s'il n'y a pas de catégorie à cet index.
	 */
	public void supprimerCategorie(int index) throws Exception {
		this.modele.getUtilisateur().supprimerCategorie(index);
		
	}
}
