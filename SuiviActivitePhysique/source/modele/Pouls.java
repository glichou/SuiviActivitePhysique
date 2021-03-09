package modele;

import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * Représentation d'une mesure de pouls
 * @author lichou
 * @version 0.1
 */
public class Pouls {
	private short frequence;
	private LocalDateTime date;
	
	/**
	 * Constructeur d'objet de la classe Pouls.
	 * @param frequence La fréquence cardiaque (en bpm).
	 */
	public Pouls(short frequence) {
		this.frequence = frequence;
		this.date = LocalDateTime.now(ZoneId.of("UTC"));
	}
	
	/**
	 * Constructeur d'objet de la classe Pouls.
	 * @param frequence La fréquence cardiaque (en bpm).
	 * @param date La date de la mesure du poul.
	 */
	public Pouls(short frequence, LocalDateTime date) {
		this.frequence = frequence;
		this.date = date;
	}
}
