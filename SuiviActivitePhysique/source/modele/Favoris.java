package modele;

import java.time.Duration;

/**
 * Représentation d'un favoris
 * @author lichou
 * @version 0.1
 */
public class Favoris {
	private String lien;
	private String memo;
	private Duration duree;
	private Categorie categorie;
	
	/**
	 * Constructeur de l'objet Favoris.
	 * @param lien Le lien vers la video.
	 * @param duree La durée approximative de l'entrainement.
	 * @param categorie La catégorie de l'entrainement.
	 * @param memo Un mémo définit par l'utilisateur.
	 */
	public Favoris(String lien, Duration duree, Categorie categorie, String memo) {
		this.lien = lien;
		this.duree = duree;
		this.categorie =  categorie;
		this.memo = memo;
	}
	
	
	/**
	 * Récupérer le lien du favoris.
	 * @return Le lien hypertexte vers le contenu.
	 */
	public String getLien() {
		return this.lien;
	}
	
	
	public String toString() {
		return "==FAVORIS==\n" + 
				"Lien: " + lien + "\n" +
				"Durée: " + duree.toMinutes() + " minutes\n" +
				"Categorie: " + categorie.name() + "\n" +
				"Mémo: " + memo;
	}
}
