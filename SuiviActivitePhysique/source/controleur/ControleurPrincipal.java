package controleur;

import modele.ModeleApplication;
import vue.VueActivites;
import vue.VuePrincipale;
import vue.VueStatistiques;
import vue.VueCategories;
import vue.VueFavoris;

/**
 * Classe controleur.
 * @author Grégoire LICHOU
 * @author Quentin COUSTURIAN
 */
public class ControleurPrincipal {
	private ModeleApplication modele;
	private VuePrincipale vue;
	
	/**
	 * Constructeur de la classe ControleurApplication.
	 * @param modele Le modèle de l'application.
	 * @param vue La vue de l'application.
	 */
	public ControleurPrincipal(ModeleApplication modele, VuePrincipale vue) {
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
		VueActivites vActivites = new VueActivites();
		new ControleurActivites(this.modele.getUtilisateur(), vActivites);
		vActivites.afficherMenu();
	}
	
	/**
	 * Passer à la vue des favoris.
	 */
	public void afficherMenuFavori() {
		VueFavoris vFavori = new VueFavoris();
		new ControleurFavoris(this.modele.getUtilisateur(), vFavori);
		vFavori.afficherMenu();
	
	}

	/**
	 * Passer à la vue des catégories.
	 */
	public void afficherMenuCategorie() {
		VueCategories vCategories = new VueCategories();
		new ControleurCategories(this.modele.getUtilisateur(), vCategories);
		vCategories.afficherMenu();	
	}
	
	/**
	 * Passer à la vue des statistiques.
	 */
	public void afficherMenuStatistique() {
		VueStatistiques vStatistiques = new VueStatistiques();
		new ControleurStatistiques(this.modele.getUtilisateur(), vStatistiques);
		vStatistiques.afficherMenu();
		
	}
}
