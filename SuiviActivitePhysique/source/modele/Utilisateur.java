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
	private ArrayList<Entrainement> favoris;
	private ArrayList<Activite> activites;
	
	/**
	 * Contructeur de l'objet Utilisateur.
	 * @param prenom Le prénom de l'utilisateur.
	 * @param nom Le nom de l'utilisateur.
	 */
	public Utilisateur(String prenom, String nom) {
		this.favoris = new ArrayList<Entrainement>();
		this.activites = new ArrayList<Activite>();
		this.prenom = prenom;
		this.nom = nom;
	}
	
	/**
	 * Ajouter un favoris dans la liste de l'utiliateur. 
	 * @param entrainement L'entrainement à ajouter dans les favoris
	 */
	public void ajouterFavoris(Entrainement entrainement) {
		this.favoris.add(entrainement);
	}
	
	/**
	 * Ajouter une activité dans la liste de l'utilisateur.
	 * @param activite L'activité à ajouter dans la liste de l'utilisateur.
	 */
	public void ajouterActivite(Activite activite) {
		this.activites.add(activite);
	}
	
	
}
