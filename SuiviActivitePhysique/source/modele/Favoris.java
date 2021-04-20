package modele;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class Favoris {
	private LocalDateTime debut;
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
	public Favoris(LocalDateTime date, String lien, Duration duree, Categorie categorie, String memo) {
		this.debut = date;
		this.lien = lien;
		this.duree = duree;
		this.categorie =  categorie;
		this.memo = memo;
	}
	
	

	public String getLien() {
		return this.lien;
	}
	

	public String toString() {
		DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("EEEE dd LLLL yyyy");
		DateTimeFormatter formatHeure = DateTimeFormatter.ofPattern("HH'h'mm");
		
		return  "Date: " + this.debut.format(formatDate) + "\n" +
				"Heure: " + this.debut.format(formatHeure) + "\n" + 
				"Lien: " + lien + "\n" +
				"Dur�e: " + duree.toMinutes() + " minutes\n" +
				"Categorie: " + categorie.getLibelle() + "\n" +
				"M�mo: " + memo;
	}
}
