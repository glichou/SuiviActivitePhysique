package modele;

import java.time.Duration;
import java.time.LocalDateTime;

/**
 * Représentation d'une activité physique.
 * @author lichou
 * @version 0.1
 */
public class Activite {
	private LocalDateTime date;
	private Difficulte difficulte;
	private Duration duree;
	private Categorie categorie;
	private long distanceParcouru;
	
	/**
	 * Constructeur de l'objet Activite.
	 * @param date La date de réalisation de l'activité
	 * @param difficulte La difficulté ressentie de l'activité
	 * @param duree La durée de l'activité
	 * @param distance La distance parcouru lors de l'activité.
	 */
	public Activite(LocalDateTime date, Difficulte difficulte, Duration duree, long distance) {
		this.date = date;
		this.difficulte = difficulte;
		this.duree = duree;
		this.distanceParcouru = distance;
	}
}
