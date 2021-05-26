package controleur;

import java.util.ArrayList;
import modele.Categorie;
import modele.Utilisateur;
import vue.VueCategorie;

/**
 * Classe controleur.
 * @author Grégoire LICHOU
 * @author Quentin COUSTURIAN
 */
public class ControleurCategorie {
	private Utilisateur modele;
	private VueCategorie vue;
	
	/**
	 * Controleur de la classe ControleurCategorie
	 * @param modele Le modèle du controleur.
	 * @param vue La vue du controleur.
	 */
	public ControleurCategorie(Utilisateur modele, VueCategorie vue) {
		this.modele = modele;
		this.vue = vue;
		this.vue.setControleur(this);
	}

	/**
	 * Récupérer l'ensemble des catégories de l'utilisateur.
	 * @return Les catégories de l'activités.
	 */
	public ArrayList<Categorie> recupererCategories() {
		return this.modele.getCategories();
	}

	/**
	 * Récupérer l'ensemble des catégories de l'utilisateur.
	 * @param index Les catégories de l'activités.
	 * @return La catégorie à l'index demandé.
	 * @throws Exception Lever une exception s'il n'y a pas de catégorie à cet index.
	 */
	public Categorie recupererCategorie(int index) throws Exception {
		return this.modele.recupererCategorie(index);
	}
	
	/**
	 * Ajouter une catégorie dans le profil de l'utilisateur.
	 * @param libelle Le libelle de la catégorie.
	 */
	public boolean ajouterCategorie(Categorie categorie) {
		return this.modele.ajouterCategorie(categorie);
	}

	/**
	 * Récupérer le nombre de catégories de l'utilisateur.
	 * @return Le nombre de catégories.
	 */
	public int recupererNbCategories() {
		return this.modele.getNbCategories();
	}

	/**
	 * Supprimer une catégorie de l'utilisateur à un index.
	 * @param index L'index de la catégorie à supprimer.
	 * @throws Exception Lever une exception s'il n'y a pas de catégorie à cet index.
	 */
	public void supprimerCategorie(int index) throws Exception {
		this.modele.supprimerCategorie(index);
		
	}
}
