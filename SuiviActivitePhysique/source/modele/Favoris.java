package modele;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Réprésentation d'un Favoris.
 * @author lichou
 * @version 0.1
 */
public class Favoris implements Serializable, Comparable<Favoris> {
	private LocalDateTime dateCreation;
	private String titre;
	private String lien;
	private String memo;
	private Duration duree;
	private Categorie categorie;
	
	/**
	 * Constructeur de l'objet Favoris.
	 * @param lien 			; Le lien vers la video.
	 * @param duree 		; La durée approximative de l'entrainement.
	 * @param categorie 	; La catégorie de l'entrainement.
	 * @param memo 			; Un mémo défini par l'utilisateur.
	 */
	public Favoris(String titre, String lien, Duration duree, Categorie categorie, String memo) {
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
		return "[" + this.categorie.getLibelle() + "] " + this.titre + " - ˹" + this.recupererDomaineLien() + "˼";
	}
	
	/**
	 * Récupérer les informations sur le Favori
	 * @return La chaine de caractères.
	 */
	public String toString() {
		DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("EEEE dd LLLL yyyy");
		DateTimeFormatter formatHeure = DateTimeFormatter.ofPattern("HH'h'mm");
		
		return  "Titre: " + this.titre + "\n" + 
				"Date de création: " + this.dateCreation.format(formatDate) + "\n" +
				"Heure de création: " + this.dateCreation.format(formatHeure) + "\n" + 
				"URL: " + lien + "\n" +
				"Durée: " + duree.toMinutes() + " minutes\n" +
				"Categorie: " + categorie.getLibelle() + "\n" +
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
    * date de création > catégorie > durée > titre > lien > memo 
    * 
    * @param activite L'activité que l'on souhaite comparer.
    * @return Trois valeurs possibles -1 pour plus petits, 0 pour égal et 1 pour supérieur.
    */
	@Override
	public int compareTo(Favoris favoris) {
		if(this.equals(favoris)) {
			return 0;
		} else {
			if(!this.dateCreation.equals(favoris.getDateCreation())) {
				return this.dateCreation.compareTo(favoris.getDateCreation());
			} else if (!this.categorie.equals(favoris.getCategorie())) {
				return this.categorie.compareTo(favoris.categorie);
			} else if(! this.duree.equals(favoris.getDuree())) {
				return this.duree.compareTo(favoris.getDuree());
			} else if(! this.titre.equals(favoris.getTitre())) {
				return this.titre.compareTo(favoris.getTitre());
			} else if(! this.lien.equals(favoris.getLien())) {
				return this.lien.compareTo(favoris.getLien());
			} else {
				return this.memo.compareTo(favoris.getMemo());
			}
		}
	}
	
    /**
     * Vérifier si deux favoris sont égales, on se base sur les attributs
     * pour déterminer s'ils sont égaux ou non.
     * date de création > catégorie > durée > titre > lien > memo 
     * @param objet L'objet que l'on souhaite comparer.
     * @return true si égal, false sinon.
     */
    @Override
   public boolean equals(Object objet) {
    	if(objet == this) {
    		//Les deux objets ont la même référence, se sont les mêmes.
    		return true;
    		
    	} else if(objet instanceof Favoris) {
    		//Vérifier si tous les favori sont identiques.
    		Favoris favoris = (Favoris) objet;
    		
    		return (this.dateCreation.equals(favoris.getDateCreation()) && 
    				this.categorie.equals(favoris.getCategorie()) && 
    				this.duree.equals(favoris.getDuree()) &&
    				this.titre.equals(favoris.getTitre()) &&
    				this.lien.equals(favoris.getLien()) &&
    				this.memo.equals(favoris.getMemo()));
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
}