package modele;

import java.time.Duration;
import java.time.LocalDateTime;

/**
 * Représentation d'une activité physique.
 * @author lichou
 * @version 0.1
 */
public class Activite {
	private LocalDateTime debut;
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
	public Activite(LocalDateTime date, Difficulte difficulte, Duration duree, long distance, Categorie categorie) {
		this.debut = date;
		this.difficulte = difficulte;
		this.duree = duree;
		this.distanceParcouru = distance;
		this.categorie = categorie;
	}

	/**
	 * Récupérer la date de début de l'activité.
	 * @return La date de début.
	 */
	public LocalDateTime getDebut() {
		return this.debut;
	}
	
	/**
	 * Récupérer le niveau de difficulté de l'activité.
	 * @return Le niveau de difficulté.
	 */
	public Difficulte getDifficulte() {
		return this.difficulte;
	}

	/**
	 * Récupérer la durée de l'exercice.
	 * @return La durée de l'exercice.
	 */
	public Duration getDuree() {
		return this.duree;
	}

	/**
	 * Récupérer la catégorie de l'activité.
	 * @return La catégorie de l'activité.
	 */
	public Categorie getCategorie() {
		return this.categorie;
	}

	/**
	 * Récupérer la distance parcouru lors de l'activité.
	 * @return La distance parcouru.
	 */
	public long getDistanceParcouru() {
		return this.distanceParcouru;
	}
	
	
}
