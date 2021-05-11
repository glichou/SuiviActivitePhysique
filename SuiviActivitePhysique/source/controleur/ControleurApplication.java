package controleur;

import modele.ModeleApplication;
import vue.VueActivite;
import vue.VueApplication;
import vue.VueCategorie;
import vue.VueFavoris;

/**
 * Classe controleur.
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

	/**
	 * Vérifier si un utilisateur est connecté.
	 * @return true si connecté, false sinon.
	 */
	public boolean utilisateurConnecte() {
		return (this.modele.getUtilisateur() != null);
	}

	/**
	 * Récupérer le nom de l'utilisateur.
	 * @return Le nom de l'utilisateur.
	 */
	public String recupererNomUtilisateur() {
		return this.modele.getUtilisateur().getNom();
	}
	
	/**
	 * Récupérer le prénom de l'utilisateur.
	 * @return Le nom de l'utilisateur.
	 */
	public String recupererPrenomUtilisateur() {
		return this.modele.getUtilisateur().getPrenom();
	}
	
	/**
	 * Passer à la vue de l'activité.
	 */
	public void afficherMenuActivite() {
		VueActivite vActivite = new VueActivite();
		ControleurActivite cActivite = new ControleurActivite(this.modele, vActivite);
		vActivite.afficherMenu();
	}
	
	/**
	 * Passer à la vue des favoris.
	 */
	public void afficherMenuFavori() {
		VueFavoris vFavori = new VueFavoris();
		ControleurFavoris cFavori = new ControleurFavoris(this.modele, vFavori);
		vFavori.afficherMenu();
	
	}

	/**
	 * Passer à la vue des catégories.
	 */
	public void afficherMenuCategorie() {
		VueCategorie vCategorie = new VueCategorie();
		ControleurCategorie cFavori = new ControleurCategorie(this.modele, vCategorie);
		vCategorie.afficherMenu();	
	}
}
