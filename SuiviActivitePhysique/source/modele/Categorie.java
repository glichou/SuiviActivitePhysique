package modele;

import java.io.Serializable;

/**
 * Représentation d'une catégorie.
 * @author lichou
 * @version 0.1
 */
public class Categorie implements Serializable, Comparable<Categorie>{
	private String libelle;
	
	/**
	 * Constructeur de la classe Catégorie.
	 * @param libelle Le libellé de la catégorie.
	 */
	public Categorie(String libelle) {
		this.libelle = libelle;
	}
	
	/**
	 * Récupérer le libellé de la catégorie.
	 * @return Le libellé de la catégorie.
	 */
	public String getLibelle() {
		return this.libelle;
	}
	
	/**
	 * Récupérer les informations sur la catégorie.
	 * @param La chaine de caractères.
	 */
	public String toString() {
		return this.libelle;
	}
	
	/**
	 * Récupérer un extrait des informations de la catégorie.
	 * @return La chaine de caractères.
	 */
	public String toSmallString() {
		return this.libelle;
	}

   /**
    * Fonction qui compare deux catégorie en fonction du nom (se base sur le 
    * compareTo du la classe String).
    * 
    * @param categorie La catégorie à comparer.
    * @return Trois valeurs possibles -1 pour plus petits, 0 pour égal et 1 pour supérieur.
    */
	@Override
	public int compareTo(Categorie categorie) {
		return this.libelle.compareToIgnoreCase(categorie.getLibelle());
	}
	
	/**
	 * 
	 */
	@Override
	public boolean equals(Object objet) {
		if (objet == this) {
			return true;
		} else if (objet instanceof Categorie) {
			Categorie categorie = (Categorie) objet;
			return this.libelle.equalsIgnoreCase(categorie.getLibelle());
		} else {
			return false;
		}
	}
	
	
}