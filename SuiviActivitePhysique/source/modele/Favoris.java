package modele;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class Favoris {
	private LocalDateTime dateCreation;
	private String titre;
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
	public Favoris(String titre, String lien, Duration duree, Categorie categorie, String memo) {
		this.titre = titre;
		this.dateCreation = LocalDateTime.now();;
		this.lien = lien;
		this.duree = duree;
		this.categorie =  categorie;
		this.memo = memo;
	}
	
	

	public String getLien() {
		return this.lien;
	}
	
	public String toSmallString() {
		return "[" + this.categorie.getLibelle() + "] " + this.titre + " - " + this.lien;
	}

	public String toString() {
		DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("EEEE dd LLLL yyyy");
		DateTimeFormatter formatHeure = DateTimeFormatter.ofPattern("HH'h'mm");
		
		return  "Titre: " + this.titre + "\n" + 
				"Date de création: " + this.dateCreation.format(formatDate) + "\n" +
				"Heure de création: " + this.dateCreation.format(formatHeure) + "\n" + 
				"URL: " + lien + "\n" +
				"Durée: " + duree.toMinutes() + " minutes\n" +
				"Categorie: " + categorie.getLibelle() + "\n" +
				"Mémo: " + (memo.isEmpty()?"(vide)":memo);
	}
}