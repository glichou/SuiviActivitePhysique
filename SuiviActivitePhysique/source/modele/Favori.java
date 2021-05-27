package modele;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Réprésentation d'un Favoris.
 * @author Grégoire LICHOU
 * @author Quentin COUSTURIAN
 * @version 0.1
 */
public class Favori implements Serializable, Comparable<Favori> {
	private static final long serialVersionUID = -4621418444296508054L;
	private LocalDateTime dateCreation;
	private String titre;
	private String lien;
	private String memo;
	private Duration duree;
	private Categorie categorie;
	
	/**
	 * Constructeur d'un Favori.
	 * @param titre Le titre du favori
	 * @param lien Lien vers une vidéo ou un site.
	 * @param duree La durée approximative de l'entrainement.
	 * @param categorie La catégorie de l'entrainement.
	 * @param memo Un mémo défini par l'utilisateur.
	 */
	public Favori(String titre, String lien, Duration duree, Categorie categorie, String memo) {
		this.titre = titre;
		this.dateCreation = LocalDateTime.now();;
		this.lien = lien;
		this.duree = duree;
		this.categorie =  categorie;
		this.memo = memo;
	}
	
	/**
	 * Récupérer un extrait des informations sur le Favori
	 * @return La chaine de caractères.
	 */
	public String toSmallString() {
		return "[" + ((categorie != null)?categorie.getLibelle():"-Aucune catégorie-") + "] " + this.titre + " - ˹" + this.recupererDomaineLien() + "˼";
	}
	
	/**
	 * Récupérer les informations sur le Favori
	 * @return La chaine de caractères.
	 */
	@Override
	public String toString() {
		DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("EEEE dd LLLL yyyy");
		DateTimeFormatter formatHeure = DateTimeFormatter.ofPattern("HH'h'mm");
		
		return  "Titre: " + this.titre + "\n" + 
				"Date de création: " + this.dateCreation.format(formatDate) + "\n" +
				"Heure de création: " + this.dateCreation.format(formatHeure) + "\n" + 
				"URL: " + lien + "\n" +
				"Durée: " + duree.toMinutes() + " minutes\n" +
				"Categorie: " + ((categorie != null)?categorie.getLibelle():"-Aucune catégorie-") + "\n" +
				"Mémo: " + (memo.isEmpty()?"(vide)":memo);
	}
	
	/**
	 * Récupérer le nom de domaine de l'URL du favori.
	 * @return Une chaine contenant le domaine.
	 */
	public String recupererDomaineLien() {
		String chaine = "";
		
		if(this.lien.length() > 0) {
			Pattern modele = Pattern.compile("(?:(?<protocole>https?)://)?(www\\.)?(?<domaine>([a-z0-9](?:[a-z0-9-]{0,61}[a-z0-9])?\\.)+[a-z][a-z0-9-]{0,61}[a-z0-9])(?:(?:(?:(?:/[a-zA-Z_0-9\\.-]+)+/?)|/)(?:\\?[^#]*)?(?:#[a-zA-Z][a-zA-Z0-9-]*[a-zA-Z0-9])?)?");
			
			Matcher expression = modele.matcher(this.lien); 
			if(expression.matches()) {
				chaine = expression.group("domaine");
			}
		}
		return chaine;
	}

	/**
    * Fonction qui compare deux favoris sur les critères suivant (dans cet ordre)
    * date de création → catégorie → durée → titre → lien → memo 
    * 
    * @param favori Le favori que l'on souhaite comparer.
    * @return Trois valeurs possibles -1 pour plus petits, 0 pour égal et 1 pour supérieur.
    */
	@Override
	public int compareTo(Favori favori) {
		if(this.equals(favori)) {
			return 0;
		} else {
			if(!this.dateCreation.equals(favori.getDateCreation())) {
				return this.dateCreation.compareTo(favori.getDateCreation());
			} else if (!this.categorie.equals(favori.getCategorie())) {
				return this.categorie.compareTo(favori.categorie);
			} else if(! this.duree.equals(favori.getDuree())) {
				return this.duree.compareTo(favori.getDuree());
			} else if(! this.titre.equals(favori.getTitre())) {
				return this.titre.compareTo(favori.getTitre());
			} else if(! this.lien.equals(favori.getLien())) {
				return this.lien.compareTo(favori.getLien());
			} else {
				return this.memo.compareTo(favori.getMemo());
			}
		}
	}
	
    /**
     * Vérifier si deux favoris sont égales, on se base sur les attributs
     * pour déterminer s'ils sont égaux ou non.
     * date de création → catégorie → durée → titre → lien → memo 
     * @param objet L'objet que l'on souhaite comparer.
     * @return true si égal, false sinon.
     */
    @Override
   public boolean equals(Object objet) {
    	if(objet == this) {
    		//Les deux objets ont la même référence, se sont les mêmes.
    		return true;
    		
    	} else if(objet instanceof Favori) {
    		//Vérifier si tous les favori sont identiques.
    		Favori favori = (Favori) objet;
    		
    		return (this.dateCreation.equals(favori.getDateCreation()) && 
    				this.categorie.equals(favori.getCategorie()) && 
    				this.duree.equals(favori.getDuree()) &&
    				this.titre.equals(favori.getTitre()) &&
    				this.lien.equals(favori.getLien()) &&
    				this.memo.equals(favori.getMemo()));
    	} else {
    		//L'objet n'est pas un favori, ils sont différents.
    		return false;
    	}
   }
	
	/**
	 * Récupérer la date de création du favori.
	 * @return La date de création du favori
	 */
	public LocalDateTime getDateCreation() {
		return this.dateCreation;
	}


	/**
	 * Récupérer le titre du favori.
	 * @return Le titre du favori.
	 */
	public String getTitre() {
		return this.titre;
	}

	/**
	 * Récupérer le mémo contenu dans le favori.
	 * @return Le mémo du favori.
	 */
	public String getMemo() {
		return this.memo;
	}

	/**
	 * Récupérer la durée approximative de l'entrainement définit dans le favori.
	 * @return La durée approximative de l'entrainement.
	 */
	public Duration getDuree() {
		return this.duree;
	}
	
	/**
	 * Récupérer le lien contenu dans le favori.
	 * @return Le lien du favori.
	 */
	public String getLien() {
		return this.lien;
	}
	
	/**
	 * Récupérer la catégorie du favori.
	 * @return La catégorie du favori.
	 */
	public Categorie getCategorie() {
		return this.categorie;
	}

	/**
	 * Modifier le titre du favoris.
	 * @param titre Le nouveau titre du favoris.
	 */
	public void setTitre(String titre) {
		this.titre = titre;
	}
	
	/**
	 * Modifier le lien du favoris.
	 * @param lien Le nouveau lien du favoris.
	 */
	public void setLien(String lien) {
		
		this.lien = lien;
	}
	
	/**
	 * Modifier le memo du favoris.
	 * @param memo Le nouveau mémo du favoris.
	 */
	public void setMemo(String memo) {
		this.memo = memo;
	}

	/**
	 * Modifier la durée approximative de l'entrainement.
	 * @param duree La durée de l'entrainement.
	 */
	public void setDuree(Duration duree) {
		this.duree = duree;
	}

	/**
	 * Modifier la catégorie du favoris.
	 * @param categorie La catégorie du favoris.
	 */
	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}
}