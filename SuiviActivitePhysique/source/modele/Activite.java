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
	private long distanceParcouru;
}
