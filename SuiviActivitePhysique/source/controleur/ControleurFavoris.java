package controleur;

import java.util.ArrayList;

import modele.ModeleApplication;
import modele.Favoris;
import vue.VueFavoris;

/**
 * Classe controleur
 * @author
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

}
