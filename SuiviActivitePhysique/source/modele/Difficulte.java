package modele;

import java.io.Serializable;

/**
 * Niveaux de difficultés d'une activité
 * échelle allant de 1 à 10.
 * @author Grégoire LICHOU
 * @author Quentin COUSTURIAN
 * @version 0.1
 */
public enum Difficulte implements Serializable, Comparable<Difficulte>{
	UN,
    DEUX,
    TROIS,
    QUATRE,
    CINQ,
    SIX,
    SEPT,
    HUIT,
    NEUF,
    DIX;
	
	/**
	 * Récupérer les informations sur la difficulté.
	 * @param La chaine de caractères.
	 */
	@Override
	public String toString() {
		String chaine = "";
		for(int i = 0; i < Difficulte.values().length; i++) {
			chaine += (i > this.ordinal())?"☆":"★";
		}
		return chaine;
	}
}
