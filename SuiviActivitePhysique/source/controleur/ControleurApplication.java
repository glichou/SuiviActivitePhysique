package controleur;

import modele.ModeleApplication;
import vue.VueApplication;

/**
 * 
 * @author lichou
 *
 */
public class ControleurApplication {
	private ModeleApplication modele;
	private VueApplication vue;
	
	/**
	 * Constructeur de la classe ControleurApplication.
	 * @param modele Le modèle de l'application.
	 * @param vue La vue de l'application.
	 */
	public ControleurApplication(ModeleApplication modele, VueApplication vue) {
		this.modele = modele;
		this.vue = vue;
		this.vue.setControleur(this);
	}
	
	/**
	 * Créer un compte pour l'utilisateur de l'application.
	 * @param prenom Le prénom de l'utilisateur.
	 * @param nom Le nom de l'utilisateur.
	 * @param taille La taille de l'utilisateur.
	 * @param poids Le poids de l'utilisateur.
	 */
	public void creerCompte(String prenom, String nom, int taille, double poids) {
		this.modele.setUtilisateur(prenom, nom, taille, poids);
	}
}
