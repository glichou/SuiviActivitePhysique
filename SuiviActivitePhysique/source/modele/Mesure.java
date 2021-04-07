package modele;

import java.time.LocalDateTime;

/**
 * Représentation d'une Mesure.
 * @author lichou
 */
public class Mesure {
	private LocalDateTime date;
	
	/**
	 * Constructeur de la classe Mesure.
	 * @param date La date de prise de la mesure.
	 */
	public Mesure(LocalDateTime date) {
		this.date = date;
	}
}
