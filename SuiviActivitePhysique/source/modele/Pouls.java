package modele;

import java.time.LocalDateTime;

/**
 * Représentation d'une mesure de pouls
 * @author Grégoire LICHOU
 * @author Quentin COUSTURIAN
 * @version 0.1
 */
public class Pouls extends Mesure {
	private static final long serialVersionUID = -4261672601021189983L;
	private short frequence;
	
	/**
	 * Constructeur d'objet de la classe Pouls.
	 * @param frequence La fréquence cardiaque (en bpm).
	 */
	public Pouls(short frequence) {
		super();
		this.frequence = frequence;
	}
	
	/**
	 * Constructeur d'objet de la classe Pouls.
	 * @param frequence La fréquence cardiaque (en bpm).
	 * @param date La date de la mesure du poul.
	 */
	public Pouls(short frequence, LocalDateTime date) {
		super(date);
		this.frequence = frequence;
	}
	
	/**
	 * Récupérer la fréquence cardiaque mesuré.
	 * @return La mesure du pouls en BPM. 
	 */
	public short getFrequenceCardiaque() {
		return this.frequence;
	}
}
