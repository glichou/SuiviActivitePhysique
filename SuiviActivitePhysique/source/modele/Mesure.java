package modele;

import java.time.LocalDateTime;
import java.time.ZoneId;

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
	 * Constructeur de la classe Mesure.
	 */
	public Mesure() {
		this.date = LocalDateTime.now(ZoneId.of("UTC"));
	}
	
	/**
	 * Récupérer la date de la prise de la mesure.
	 * @return La date de prise de la mesure.
	 */
	public LocalDateTime getDate() {
		return this.date;
	}
	
	/**
	 * Fonction qui compare deux mesures entres-elles
	 * en se basant sur la date de prise de la mesure.
	 * @param mesure La mesure à comparer.
	 * @return Trois valeurs possibles -1 pour plus petits, 0 pour égal et 1 pour supérieur.
	 */
	/**
	public int compareTo(Mesure mesure) {
		if(this.equals(mesure)) {
			return 0;
		} else {
			return this.date.compareTo(mesure.getDate());
		}
	}
	
	public boolean equals(Object objet) {
    	if(objet == this) {
    		//Les deux objets ont la même référence, se sont les mêmes.
    		return true;
    		
    	} else if(objet instanceof Mesure) {
    		//Vérifier si tous les favori sont identiques.
    		Mesure mesure = (Mesure) objet;
    		
    		return this.date.equals(mesure.getDate());
    	} else {
    		return false;
    	}
   }**/
}
