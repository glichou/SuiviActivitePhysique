package modele;

/**
 * Représentation d'une catégorie.
 * @author lichou
 * @version 0.1
 */
public class Categorie {
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
	 * Récupérer les informations sur la
	 * catégorie.
	 */
	public String toString() {
		return this.libelle;
	}
}