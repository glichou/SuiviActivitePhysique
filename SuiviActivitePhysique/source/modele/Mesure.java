package modele;

import java.time.LocalDateTime;

/**
 * Représentation abstraite d'une Mesure.
 * @author lichou
 */
public abstract class Mesure {
	private LocalDateTime date;
	
	/**
	 * Constructeur de la classe Mesure.
	 * @param date La date de prise de la mesure.
	 */
	public Mesure(LocalDateTime date) {
		this.date = date;
	}
	
	/**
	 * Récupérer la date de la prise de la mesure.
	 * @return La date de prise de la mesure.
	 */
	public LocalDateTime getDate() {
		return this.date;
	}
}
