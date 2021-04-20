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
	private ArrayList<Mesure> mesures;
	private ArrayList<Categorie> categories;
	
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
		this.mesures = new ArrayList<Mesure>();
		this.categories = new ArrayList<Categorie>();
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
	 * Ajouter une mesure de l'utilisateur.
	 * @param mesure La mesure à ajouter dans le profil de l'utilisateur.
	 */
	public void ajouterMesure(Mesure mesure) {
		this.mesures.add(mesure);
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
	 * Récupérer les mesure de l'utilisateur.
	 * @return Les mesures de l'utilisateur.
	 */
	public ArrayList<Mesure> getMesures(){
		return this.mesures;
	}
	
	/**
	 * Récupérer le prénom de l'utilisateur.
	 * @return Le prénom de l'utilisateur.
	 */
	public String getPrenom() {
		return this.prenom;
	}

	/**
	 * Récupérer les catégories de l'utilisateur.
	 * @return Les catégories de l'utilisateur.
	 */
	public ArrayList<Categorie> getCategories() {
		return this.categories;
	}
	
	/**
	 * Récupérer les informations de l'utilisateur.
	 */
	public String toString() {
		return "==UTILISATEUR==\n" +
				"Nom: " + nom + "\n" +
				"Prénom: " + prenom + "\n" +
				"Taille: " + taille + "cm\n" +
				"Poids: " + poids + "kg\n";
	}
	
	/**
	 * Ajouter une categorie dans le profil utilisateur.
	 * @param categorie La catégorie de l'utilisateur.
	 */
	public void addCategorie(Categorie categorie) {
		this.categories.add(categorie);
	}
}
