package controleur;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Optional;

import modele.Utilisateur;
import modele.Categorie;
import modele.Favori;
import vue.VueCategories;
import vue.VueFavoris;

/**
 * Classe du controleur favoris
 * @author Grégoire LICHOU
 * @author Quentin COUSTURIAN
 */
public class ControleurFavoris {
	private Utilisateur modele;
	private VueFavoris vue;
	
	/**
	 * Constructeur de la classe.
	 * @param modele Le modèle du controleur.
	 * @param vue La vue du controleur.
	 */
	public ControleurFavoris(Utilisateur modele, VueFavoris vue) {
		this.modele = modele;
		this.vue = vue;
		this.vue.setControleur(this);
	}
	
	/**
	 * Récupérer les favoris de l'utilisateur.
	 * @return Les favoris de l'utilisateur.
	 */
	public ArrayList<Favori> recupererFavoris(){
		return this.modele.getFavoris();
	}
	
	
	/**
	 * Ajouter un favori dans le profil de l'utilisateur.
	 * @param titre Le titre du favoris
	 * @param lien	Le lien vers l'URL du favori.
	 * @param duree La durée estimée de l'entrainement.
	 * @param categorie La catégorie du favori.
	 * @param memo Le mémo du favori.
	 */	
	public void ajouterUnFavori(String titre, String lien, Duration duree, Categorie categorie, String memo) {
		Favori favori = new Favori(titre, lien, duree, categorie, memo);
		this.modele.ajouterFavori(favori);
	}

	/**
	 * Afficher la vue permettant à l'utilisateur de sélectionner la
	 * catégorie voulu ou d'en créer une nouvelle.
	 * @return La catégorie sélectionné.
	 */
	public Categorie afficherSelectionCategorie() {
		return afficherSelectionCategorie(null);
	}
	
	/**
	 * Afficher la vue permettant à l'utilisateur de sélectionner la 
	 * catégorie voulu ou d'en créer une nouvelle.
	 * @param valeurParDefaut La catégorie selectionné par défaut.
	 * @return La catégorie sélectionné.
	 */
	public Categorie afficherSelectionCategorie(Categorie valeurParDefaut) {
		VueCategories vCategorie = new VueCategories();
		new ControleurCategories(this.modele, vCategorie);
		
		if(valeurParDefaut != null) {	
			return vCategorie.selectionnerCategorie(Optional.of(valeurParDefaut));
		} else {
			return vCategorie.selectionnerCategorie(Optional.empty());
		}
	}
	
	/**
	 * Récuperer le nombre de favoris de l'utilisateur.
	 * @return Le nombre de favoris.
	 */
	public int recupererNbFavoris() {
		return this.modele.recupererNbFavoris();
	}
	
	
	/**
	 * Récupérer un favoris de l'utilisateur.
	 * @param index L'index du favoris à récupérer.
	 * @return Le favoris à l'index donné.
	 * @throws Exception
	 */
	public Favori recupererFavoris(int index) throws Exception {
		return this.modele.recupererFavori(index);
	}
	
	/**
	 * Supprimer un favoris de l'utilisateur.
	 * @param index L'index du favoris à supprimer.
	 * @throws Exception Lever une exception si le favoris n'existe pas.
	 */
	public void supprimerFavoris(int index) throws Exception {
		this.modele.supprimerFavori(index);
	}
}
