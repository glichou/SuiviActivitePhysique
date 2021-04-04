package controleur;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;

import modele.Activite;
import modele.Categorie;
import modele.Difficulte;
import modele.ModeleApplication;
import vue.VueActivite;
import vue.VueCategorie;

public class ControleurActivite {
	private ModeleApplication modele;
	private VueActivite vue;
	
	/**
	 * Constructeur de la classe ControleurActivité.
	 * @param modele Le modèle de l'application.
	 * @param vue La vue de l'activité.
	 */
	public ControleurActivite(ModeleApplication modele, VueActivite vue) {
		this.modele = modele;
		this.vue = vue;
		this.vue.setControleur(this);
	}
	
	/**
	 * Récupérer l'ensemble des activités de l'utilisateur.
	 * @return Les activités de l'utilisateur.
	 */
	public ArrayList<Activite> recupererActivites(){
		return this.modele.getUtilisateur().getActivites();
	}
	
	public void ajouterUneActivite(LocalDateTime date, Difficulte difficulte, Duration duree, long distance, Categorie categorie) {
		Activite activite = new Activite(date, difficulte, duree, distance, categorie);
		this.modele.ajouterActivite(activite);
	}
	
	public Categorie afficherSelectionCategorie() {
		VueCategorie vCategorie = new VueCategorie();
		ControleurCategorie cCategorie = new ControleurCategorie(this.modele, vCategorie);
		return vCategorie.selectionnerCategorie();
	}
}
