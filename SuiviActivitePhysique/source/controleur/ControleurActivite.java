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

/**
 * Classe controleur.
 * @author lichou
 */
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
	
	/**
	 * Récupérer l'ensemble des activités de l'utilisateur.
	 * @return Les activités de l'utilisateur.
	 */
	public int recupererNbActivites(){
		return this.modele.getUtilisateur().recupererNbActivites();
	}
	
	/**
	 * Ajouter une activité dans le profil de l'utilisateur.
	 * @param date 			; La date de début de l'activité.
	 * @param difficulte 	; La difficulté de l'activité.
	 * @param duree 		; La durée de l'activité.
	 * @param distance 		; La distance parcourue lors de l'activité.
	 * @param categorie 	; La catégorie de l'activité.
	 */
	public void ajouterUneActivite(LocalDateTime date, Difficulte difficulte, Duration duree, long distance, Categorie categorie) {
		Activite activite = new Activite(date, difficulte, duree, distance, categorie);
		this.modele.ajouterActivite(activite);
	}
	
	/**
	 * Récupérer l'activité de l'utilisateur à l'index donné.
	 * @param index L'index de l'activité que l'on souhaite.
	 * @return L'activité à l'index demandé.
	 * @throws Exception Lever une exception si l'activité n'existe pas.
	 */
	public Activite recupererActivite(int index) throws Exception {
		return this.modele.getUtilisateur().recupererActivite(index);
	}
	
	/**
	 * Supprimer l'activité de l'utilisateur à l'index donné.
	 * @param index L'index de l'activité à supprimer.
	 * @return 
	 * @throws Exception Lever une exception si l'activité n'existe pas.
	 */
	public void supprimerActivite(int index) throws Exception {
		this.modele.getUtilisateur().supprimerActivite(index);
	}
	
	/**
	 * Afficher la vue permettant à l'utilisateur de 
	 * sélectionner la catégorie voulu ou d'en créer
	 * une nouvelle.
	 * @return La catégorie sélectionné.
	 */
	public Categorie afficherSelectionCategorie() {
		VueCategorie vCategorie = new VueCategorie();
		ControleurCategorie cCategorie = new ControleurCategorie(this.modele, vCategorie);
		return vCategorie.selectionnerCategorie();
	}
}
