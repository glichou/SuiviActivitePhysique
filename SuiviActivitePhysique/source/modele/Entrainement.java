package modele;

import java.time.Duration;

/**
 * Représentation d'un entrainement
 * @author lichou
 * @version 0.1
 */
public class Entrainement {
	private String lien;
	private String memo;
	private Duration duree;
	private Categorie categorie;
	
	/**
	 * Constructeur de l'objet d'entrainement.
	 * @param lien Le lien vers la video.
	 * @param duree La durée approximative de l'entrainement.
	 * @param categorie La catégorie de l'entrainement.
	 * @param memo Un mémo définit par l'utilisateur.
	 */
	public Entrainement(String lien, Duration duree, Categorie categorie, String memo) {
		this.lien = lien;
		this.duree = duree;
		this.categorie =  categorie;
		this.memo = memo;
	}
	
	public String toString() {
		return "==ENTRAINEMENT==\n" + 
				"Lien: " + lien + "\n" +
				"Durée: " + duree.toMinutes() + " minutes\n" +
				"Categorie: " + categorie.name() + "\n" +
				"Mémo: " + memo;
	}
}
