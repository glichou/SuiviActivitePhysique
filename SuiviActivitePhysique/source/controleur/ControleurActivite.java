package controleur;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

import modele.Activite;
import modele.Categorie;
import modele.Difficulte;
import modele.Utilisateur;
import vue.VueActivite;
import vue.VueCategorie;

/**
 * Classe controleur.
 * @author Grégoire LICHOU
 * @author Quentin COUSTURIAN
 */
public class ControleurActivite {
	private Utilisateur modele;
	private VueActivite vue;
	
	/**
	 * Constructeur de la classe ControleurActivité.
	 * @param modele Le modèle de l'application.
	 * @param vue La vue de l'activité.
	 */
	public ControleurActivite(Utilisateur modele, VueActivite vue) {
		this.modele = modele;
		this.vue = vue;
		this.vue.setControleur(this);
	}
	
	/**
	 * Récupérer l'ensemble des activités de l'utilisateur.
	 * @return Les activités de l'utilisateur.
	 */
	public ArrayList<Activite> recupererActivites(){
		return this.modele.getActivites();
	}
	
	/**
	 * Récupérer l'ensemble des catégories de l'utilisateur.
	 * @return Les catégories de l'utilisateur.
	 */
	public ArrayList<Categorie> recupererCategories(){
		return this.modele.getCategories();
	}
	
	/**
	 * Récupérer l'ensemble des activités de l'utilisateur.
	 * @return Les activités de l'utilisateur.
	 */
	public int recupererNbActivites(){
		return this.modele.recupererNbActivites();
	}
	
	/**
	 * Ajouter une activité dans le profil de l'utilisateur.
	 * @param date La date de début de l'activité.
	 * @param difficulte La difficulté de l'activité.
	 * @param duree La durée de l'activité.
	 * @param distance La distance parcourue lors de l'activité.
	 * @param categorie La catégorie de l'activité.
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
		return this.modele.recupererActivite(index);
	}
	
	/**
	 * Supprimer l'activité de l'utilisateur à l'index donné.
	 * @param index L'index de l'activité à supprimer.
	 * @return 
	 * @throws Exception Lever une exception si l'activité n'existe pas.
	 */
	public void supprimerActivite(int index) throws Exception {
		this.modele.supprimerActivite(index);
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
		VueCategorie vCategorie = new VueCategorie();
		new ControleurCategorie(this.modele, vCategorie);
		
		if(valeurParDefaut != null) {	
			return vCategorie.selectionnerCategorie(Optional.of(valeurParDefaut));
		} else {
			return vCategorie.selectionnerCategorie(Optional.empty());
		}
	}
}
