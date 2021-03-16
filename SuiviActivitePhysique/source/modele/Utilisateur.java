package modele;

import java.util.ArrayList;

/**
 * Représentation d'un utilisateur
 * @author lichou
 * @version 0.1
 */
public class Utilisateur {
	private String nom;
	private String prenom;
	private int taille;
	private double poids;
	private ArrayList<Favoris> favoris;
	private ArrayList<Activite> activites;
	private ArrayList<Pouls> pouls;
	
	/**
	 * Contructeur de l'objet Utilisateur.
	 * @param prenom Le prénom de l'utilisateur.
	 * @param nom Le nom de l'utilisateur.
	 * @param taille La taille de l'utilisateur en mètre.
	 * @param poids Le poids de l'utilisateur en kg.
	 */
	public Utilisateur(String prenom, String nom, int taille, double poids) {
		this.favoris = new ArrayList<Favoris>();
		this.activites = new ArrayList<Activite>();
		this.prenom = prenom;
		this.nom = nom.toUpperCase();
		this.taille = taille;
		this.poids = poids;
	}
	
	/**
	 * Ajouter un favoris dans la liste de l'utiliateur. 
	 * @param favoris L'entrainement à ajouter dans les favoris
	 */
	public void ajouterFavoris(Favoris favoris) {
		this.favoris.add(favoris);
	}
	
	/**
	 * Ajouter une activité dans la liste de l'utilisateur.
	 * @param activite L'activité à ajouter dans la liste de l'utilisateur.
	 */
	public void ajouterActivite(Activite activite) {
		this.activites.add(activite);
	}
	
	/**
	 * Ajouter une mesure du pouls de l'utilisateur.
	 * @param mesure La mesure à ajouter dans le profil de l'utilisateur.
	 */
	public void ajouterMesurePouls(Pouls mesure) {
		this.pouls.add(mesure);
	}
	
	/**
	 * Récupérer les favoris de l'utilisateur.
	 * @return Les favoris de l'utilisateur.
	 */
	public ArrayList<Favoris> getFavoris() {
		return this.favoris;
	}

	/**
	 * Récupérer les activités de l'utilisateur.
	 * @return Les activités de l'utilisateur.
	 */
	public ArrayList<Activite> getActivites() {
		return this.activites;
	}
	
	/**
	 * Récupérer les mesure de pouls de l'utilisateur.
	 * @return Les mesures de pouls de l'utilisateur.
	 */
	public ArrayList<Pouls> getMesurePouls(){
		return this.pouls;
	}
	
	/**
	 * Récupérer le prénom de l'utilisateur.
	 * @return Le prénom de l'utilisateur.
	 */
	public String getPrenom() {
		return this.prenom;
	}
	
	public String toString() {
		return "==UTILISATEUR==\n" +
				"Nom: " + nom + "\n" +
				"Prénom: " + prenom + "\n";
	}
}