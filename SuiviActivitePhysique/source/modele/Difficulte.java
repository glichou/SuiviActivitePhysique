package modele;

import java.io.Serializable;

/**
 * Niveaux de difficultés d'une activité
 * échelle allant de 1 à 10.
 * @author lichou
 * @version 0.1
 */
public class Difficulte implements Serializable, Comparable<Difficulte>{
	private Niveau niveau;
	
	public enum Niveau {
	    UN,
	    DEUX,
	    TROIS,
	    QUATRE,
	    CINQ,
	    SIX,
	    SEPT,
	    HUIT,
	    NEUF,
	    DIX
	}
	
	/**
	 * Constructeur de la classe Difficulté.
	 * @param niveau Le niveau de difficulté.
	 */
	public Difficulte(Niveau niveau) {
		this.niveau = niveau;
	}
	
	/**
	 * Récupérer les informations sur la difficulté.
	 * @param La chaine de caractères.
	 */
	@Override
	public String toString() {
		String chaine = "";
		switch(this.niveau) {
			case UN:
				chaine = "★☆☆☆☆☆☆☆☆☆";
				break;
			case DEUX:
				chaine = "★★☆☆☆☆☆☆☆☆";
				break;
			case TROIS:
				chaine = "★★★☆☆☆☆☆☆☆";
				break;
			case QUATRE:
				chaine = "★★★★☆☆☆☆☆☆";
				break;
			case CINQ:
				chaine = "★★★★★☆☆☆☆☆";
				break;
			case SIX:
				chaine = "★★★★★★☆☆☆☆";
				break;
			case SEPT:
				chaine = "★★★★★★★☆☆☆";
				break;
			case HUIT:
				chaine = "★★★★★★★★☆☆";
				break;
			case NEUF:
				chaine = "★★★★★★★★★☆";
				break;
			case DIX:
				chaine = "★★★★★★★★★★";
				break;
		}
		return chaine;
	}

   /**
    * Fonction qui compare deux difficulté en fonction de son niveau
    * 
    * @param difficulte La difficulté à comparer.
    * @return Trois valeurs possibles -1 pour moins dificile, 0 pour identique et 1 pour plus difficile.
    */
	@Override
	public int compareTo(Difficulte difficulte) {
		return this.niveau.compareTo(difficulte.getNiveau());
	}

	/**
	 * Récupérer le niveau de la difficulte.
	 * @return Le niveau de la difficulté.
	 */
	private Niveau getNiveau() {
		return this.niveau;
	}
}
