package modele;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Représentation d'un utilisateur
 * @author Grégoire LICHOU
 * @author Quentin COUSTURIAN
 * @version 0.1
 */
public class Utilisateur implements Serializable {
	private static final long serialVersionUID = 6904217260965473460L;
	private String nom;
	private String prenom;
	private int taille;
	private double poids;
	private ArrayList<Favori> favoris;
	private ArrayList<Activite> activites;
	private ArrayList<Mesure> mesures;
	private ArrayList<Categorie> categories;
	
	/**
	 * Contructeur de l'objet Utilisateur.
	 * @param prenom Le prénom de l'utilisateur.
	 * @param nom Le nom de l'utilisateur.
	 * @param taille La taille de l'utilisateur en mètre.
	 * @param poids Le poids de l'utilisateur en kg.
	 */
	public Utilisateur(String prenom, String nom, int taille, double poids) {
		this.favoris = new ArrayList<Favori>();
		this.activites = new ArrayList<Activite>();
		this.prenom = prenom;
		this.nom = nom.toUpperCase();
		this.taille = taille;
		this.poids = poids;
		this.mesures = new ArrayList<Mesure>();
		this.categories = new ArrayList<Categorie>();
	}
	
	/**
	 * Ajouter un favoris dans la liste de l'utiliateur. 
	 * @param favori L'entrainement à ajouter dans les favoris
	 */
	public void ajouterFavori(Favori favori) {
		this.favoris.add(favori);
	}
	
	/**
	 * Ajouter une activité dans la liste de l'utilisateur.
	 * @param activite L'activité à ajouter dans la liste de l'utilisateur.
	 */
	public void ajouterActivite(Activite activite) {
		this.activites.add(activite);
	}
	
	/**
	 * Récupérer le nombre de favoris de l'utilisateur.
	 * @return Le nombre de favoris.
	 */
	public int recupererNbActivites() {
		return this.activites.size();
	}
	
	/**
	 * Ajouter une mesure de l'utilisateur.
	 * @param mesure La mesure à ajouter dans le profil de l'utilisateur.
	 */
	public void ajouterMesure(Mesure mesure) {
		this.mesures.add(mesure);
	}
	
	/**
	 * Vérifier un favoris existe dans la liste de l'utilisateur.
	 * @param favori Le favoris dont ont souhaite vérifier l'existance.
	 * @return true s'il existe, false sinon.
	 */
	public boolean favorisExiste(Favori favori) {
		return this.favoris.contains(favori);
	}
	
	/**
	 * Récupérer un favoris à un index particulier.
	 * @param index L'index du favoris.
	 * @return Le favoris à l'index donné.
	 * @throws Exception Lever une exception s'il n'y a
	 * pas de favoris à cet index.
	 */
	public Favori recupererFavori(int index) throws Exception {
		if(index >= 0 && index < this.favoris.size()) {
			return this.favoris.get(index);
		} else {
			throw new Exception();
		}
	}
	
	/**
	 * Supprimer une activité de la liste de l'utilisateur.
	 * @param index L'index de l'activité à supprimer.
	 * @throws Exception Lever une exception si l'activité n'existe pas.
	 */
	public void supprimerActivite(int index) throws Exception {
		if(index >= 0 && index < this.activites.size()) {
			this.activites.remove(index);
		} else {
			throw new Exception();
		}
	}
	
	/**
	 * Récupérer le nombre de favoris de l'utilisateur.
	 * @return Le nombre de favoris.
	 */
	public int recupererNbFavoris() {
		return this.favoris.size();
	}
	
	/**
	 * Récupérer les favoris de l'utilisateur.
	 * @return Les favoris de l'utilisateur.
	 */
	public ArrayList<Favori> getFavoris() {
		return this.favoris;
	}

	/**
	 * Récupérer les activités de l'utilisateur.
	 * @return Les activités de l'utilisateur.
	 */
	public ArrayList<Activite> getActivites() {
		return this.activites;
	}
	
	/**
	 * Récupérer l'activité d'un utilisateur à un index.
	 * @param index L'index de l'activité à récupérer.
	 * @return L'activité à l'index demandé.
	 * @throws Exception Leve une exception s'il n'y a pas d'activité à cet index.
	 */
	public Activite recupererActivite(int index) throws Exception {
		if(index >= 0 && index < this.activites.size()) {
			return this.activites.get(index);
		} else {
			throw new Exception();
		}
	}
	
	/**
	 * Récupérer les mesure de l'utilisateur.
	 * @return Les mesures de l'utilisateur.
	 */
	public ArrayList<Mesure> getMesures(){
		return this.mesures;
	}
	
	/**
	 * Récupérer le prénom de l'utilisateur.
	 * @return Le prénom de l'utilisateur.
	 */
	public String getPrenom() {
		return this.prenom;
	}
	
	/**
	 * Récupérer le nom de l'utilisateur.
	 * @return Le prénom de l'utilisateur.
	 */
	public String getNom() {
		return this.nom;
	}

	/**
	 * Récupérer les catégories de l'utilisateur.
	 * @return Les catégories de l'utilisateur.
	 */
	public ArrayList<Categorie> getCategories() {
		return this.categories;
	}
	
	/**
	 * Vérifier une catégorie existe dans la liste de l'utilisateur.
	 * @param categorie La catégorie dont ont souhaite vérifier l'existance.
	 * @return true s'il existe, false sinon.
	 */
	public boolean categorieExiste(Categorie categorie) {
		return this.categories.contains(categorie);
	}
	
	/**
	 * Récupérer une catégorie à un index particulier.
	 * @param index L'index de la catégorie.
	 * @return La catégorie à l'index donné.
	 * @throws Exception Lever une exception s'il n'y a
	 * pas de catégorie à cet index.
	 */
	public Categorie recupererCategorie(int index) throws Exception {
		if(index >= 0 && index < this.categories.size()) {
			return this.categories.get(index);
		} else {
			throw new Exception();
		}
	}
	
	/**
	 * Récupérer le nombre de catégorie de l'utilisateur.
	 * @return Le nombre de catégorie.
	 */
	public int getNbCategories() {
		return this.categories.size();
	}
	
	/**
	 * Récupérer les informations de l'utilisateur.
	 */
	@Override
	public String toString() {
		return "==UTILISATEUR==\n" +
				"Nom: " + nom + "\n" +
				"Prénom: " + prenom + "\n" +
				"Taille: " + taille + "cm\n" +
				"Poids: " + poids + "kg\n";
	}
	
	/**
	 * Ajouter une categorie dans le profil utilisateur.
	 * @param categorie La catégorie de l'utilisateur.
	 */
	public boolean ajouterCategorie(Categorie categorie) {
		return (!categorieExiste(categorie) && this.categories.add(categorie));
	}

	/**
	 * Supprimer un favoris de la liste de l'utilisateur.
	 * @param index L'index du favoris à supprimer.
	 * @throws Exception Lever une exception si le favoris n'existe pas.
	 */
	public void supprimerFavori(int index) throws Exception {
		if(index >= 0 && index < this.favoris.size()) {
			this.favoris.remove(index);
		} else {
			throw new Exception();
		}
	}
	
	/**
	 * Supprimer une catégorie de l'utilisateur.
	 * @param index L'index de la catégorie à supprimer.
	 * @throws Exception Lever une exception si la catégorie n'existe pas.
	 */
	public void supprimerCategorie(int index) throws Exception {
		if(index >= 0 && index < this.categories.size()) {
			
			Categorie categorie = this.categories.get(index);
			
			//Supprimer la catégorie des activités existantes.
			this.remplacerCategorieActivites(categorie, null);
			
			//Supprimer la catégorie des favoris existants.
			this.remplacerCategorieFavoris(categorie, null);
			
			this.categories.remove(index);
		} else {
			throw new Exception();
		}
	}
	
	/**
	 * Remplacer une catégorie par une autre dans la liste des activités
	 * de l'utilisateur.
	 * @param ancienne L'ancienne catégorie que l'on souhaite remplacer.
	 * @param nouvelle La nouvelle catégorie qui remplacera l'ancienne.
	 */
	public void remplacerCategorieActivites(Categorie ancienne, Categorie nouvelle) {
		for(Activite activite : this.activites) {
			if(activite.getCategorie().equals(ancienne)) {
				activite.setCategorie(nouvelle);
			}
		}
	}
	
	/**
	 * Remplacer une catégorie par une autre dans la liste des favoris
	 * de l'utilisateur.
	 * @param ancienne L'ancienne catégorie que l'on souhaite remplacer.
	 * @param nouvelle La nouvelle catégorie qui remplacera l'ancienne.
	 */
	public void remplacerCategorieFavoris(Categorie ancienne, Categorie nouvelle) {
		for(Favori favori : this.favoris) {
			if(favori.getCategorie().equals(ancienne)) {
				favori.setCategorie(nouvelle);
			}
		}
	}
}
