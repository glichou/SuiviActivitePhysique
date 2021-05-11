package controleur;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;

import modele.ModeleApplication;
import modele.Activite;
import modele.Categorie;
import modele.Difficulte;
import modele.Favoris;
import vue.VueCategorie;
import vue.VueFavoris;

/**
 * Classe controleurFavoris
 * @author quent
 */
public class ControleurFavoris {
	private ModeleApplication modele;
	private VueFavoris vue;
	
	/**
	 * Constructeur de la classe.
	 * @param modele Le modèle du controleur.
	 * @param vue La vue du controleur.
	 */
	public ControleurFavoris(ModeleApplication modele, VueFavoris vue) {
		this.modele = modele;
		this.vue = vue;
		this.vue.setControleur(this);
	}
	
	/**
	 * Récupérer les favoris de l'utilisateur.
	 * @return Les favoris de l'utilisateur.
	 */
	public ArrayList<Favoris> recupererFavoris(){
		return this.modele.getUtilisateur().getFavoris();
	}
	
	
	/**
	 * Ajouter un favori dans le profil de l'utilisateur.
	 * @param titre 	; C'est le titre du favori.
	 * @param lien 	 	; C'est le lien du favori.
	 * @param duree 	; C'est la durée du favori.
	 * @param categorie ; C'est la catégorie du favori.
	 * @param memo 		; C'est le mémo du favori.
	 */	
	public void ajouterUnFavori(String titre, String lien, Duration duree, Categorie categorie, String memo) {
		Favoris favoris = new Favoris(titre, lien, duree, categorie, memo);
		this.modele.ajouterUnFavori(favoris);
	}

	/**
	 * Afficher la vue permettant à l'utilisateur de 
	 * sélectionner la catégorie voulue ou d'en créer
	 * une nouvelle.
	 * @return La catégorie sélectionnée.
	 */	
	public Categorie afficherSelectionCategorie() {
		VueCategorie vCategorie = new VueCategorie();
		ControleurCategorie cCategorie = new ControleurCategorie(this.modele, vCategorie);
		return vCategorie.selectionnerCategorie();
	}
}
