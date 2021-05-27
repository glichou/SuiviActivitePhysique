package modele;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Représentation d'une activité physique.
 * @author Grégoire LICHOU
 * @author Quentin COUSTURIAN
 * @version 0.1
 */
public class Activite implements Serializable, Comparable<Activite> {
	private static final long serialVersionUID = -6462122725015687985L;
	private LocalDateTime debut;
	private Difficulte difficulte;
	private Duration duree;
	private Categorie categorie;
	private long distanceParcouru;
	
	/**
	 * Constructeur de l'objet Activite.
	 * @param date La date de réalisation de l'activité
	 * @param difficulte La difficulté ressentie de l'activité
	 * @param duree La durée de l'activité
	 * @param distance La distance parcouru lors de l'activité.
	 * @param categorie La catégorie de l'activité.
	 */
	public Activite(LocalDateTime date, Difficulte difficulte, Duration duree, long distance, Categorie categorie) {
		this.debut = date;
		this.difficulte = difficulte;
		this.duree = duree;
		this.distanceParcouru = distance;
		this.categorie = categorie;
	}

	/**
	 * Récupérer la date de début de l'activité.
	 * @return La date de début.
	 */
	public LocalDateTime getDebut() {
		return this.debut;
	}
	
	/**
	 * Récupérer le niveau de difficulté de l'activité.
	 * @return Le niveau de difficulté.
	 */
	public Difficulte getDifficulte() {
		return this.difficulte;
	}

	/**
	 * Récupérer la durée de l'exercice.
	 * @return La durée de l'exercice.
	 */
	public Duration getDuree() {
		return this.duree;
	}

	/**
	 * Récupérer la catégorie de l'activité.
	 * @return La catégorie de l'activité.
	 */
	public Categorie getCategorie() {
		return this.categorie;
	}

	/**
	 * Récupérer la distance parcouru lors de l'activité.
	 * @return La distance parcouru.
	 */
	public long getDistanceParcouru() {
		return this.distanceParcouru;
	}
	
	/**
	 * Récupérer un extrait des informations de l'activités
	 * @return La chaine de caractères
	 */
	public String toSmallString() {
		DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("EEEE dd LLLL yyyy 'à' HH'h'mm");
		return "[" + ((this.categorie != null)?this.categorie.getLibelle():"-Aucune catégorie-") + "] " + this.debut.format(formatDate) + " (" + this.duree.toMinutes() + " minutes)";
	}
	
	/**
	 * Récupérer les informations sur l'activité.
	 * @return La chaine de caractères
	 */
	@Override
	public String toString() {
		//Définir les formats pour la date et pour l'heure de début de l'activité.
		DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("EEEE dd LLLL yyyy");
		DateTimeFormatter formatHeure = DateTimeFormatter.ofPattern("HH'h'mm");
		
		//Retourner les informations sur l'activité.
		return 	"Catégorie: " + ((this.categorie != null)?this.categorie.getLibelle():"-Aucune catégorie-") + "\n" +
				"Date: " + this.debut.format(formatDate) + "\n" +
				"Heure: " + this.debut.format(formatHeure) + "\n" +
				"Durée: " + this.duree.toMinutes() + " minutes" + "\n" +
				"Difficultée: " + this.difficulte.toString() + "\n" +
				"Distance: " + this.distanceParcouru + " km" + "\n";
	}

   /**
    * Fonction qui compare deux activités sur les critères suivant (dans cet ordre)
    * début > durée > disatance parcouru > difficulte > categorie.
    * 
    * @param activite L'activité que l'on souhaite comparer.
    * @return Trois valeurs possibles -1 pour plus petits, 0 pour égal et 1 pour supérieur.
    */
	@Override
	public int compareTo(Activite activite) {
	   if(this.equals(activite)) {
		   return 0;
	   } else {
		   if(! this.debut.equals(activite.getDebut())) {
			   return this.debut.compareTo(activite.getDebut());
		   } else if(! this.duree.equals(activite.getDuree())) {
			   return this.duree.compareTo(activite.getDuree());
		   } else if(this.distanceParcouru != activite.getDistanceParcouru()) {
			   if(this.distanceParcouru < activite.getDistanceParcouru()) {
				   return -1;
			   } else {
				   return 1;
			   }
		   } else if(!this.difficulte.equals(activite.getDifficulte())) {
			   return this.difficulte.compareTo(activite.getDifficulte());
		   } else {
			   return this.categorie.compareTo(activite.getCategorie());
		   }
	   }
	}
	
    /**
     * Vérifier si deux activités sont égales, on se base sur les attributs
     * pour déterminer s'ils sont égaux ou non.
     * @param objet L'objet que l'on souhaite comparer.
     * @return true si égal, false sinon.
     */
    @Override
    public boolean equals(Object objet) {
    	if(objet == this) {
    		//Les deux objets ont la même référence, se sont les mêmes.
    		return true;
	
		} else if(objet instanceof Activite) {
			//L'objet est un employé, se sont les même s'ils ont le même nom, prénom et date d'embauche.
			Activite activite = (Activite) objet;
			
			return (this.debut.equals(activite.getDebut()) && 
					this.difficulte.equals(activite.getDifficulte()) && 
					this.duree.equals(activite.getDuree()) &&
					this.categorie.equals(activite.getCategorie()) &&
					this.distanceParcouru == activite.getDistanceParcouru());
		} else {
			//L'objet n'est pas un employé, ils sont différents.
		    		return false;
		}
	}
    
    /**
     * Modifier la date de début de l'activité.
     * @param debut La nouvelle date de début de l'activité.
     */
	public void setDebut(LocalDateTime debut) {
		this.debut = debut;
	}

	/**
	 * Modifier la difficulté de l'activité.
	 * @param difficulte La nouvelle difficulté de l'activité.
	 */
	public void setDifficulte(Difficulte difficulte) {
		this.difficulte = difficulte;
	}

	/**
	 * Modifier la durée de l'activité.
	 * @param duree La nouvelle durée de l'activité.
	 */
	public void setDuree(Duration duree) {
		this.duree = duree;
	}

	/**
	 * Modifier la catégorie de l'activité.
	 * @param categorie La nouvelle catégorie de l'activité.
	 */
	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}

	/**
	 * Modifier la distance parcouru de l'activité.
	 * @param distanceParcouru La nouvelle distance parcouru de l'activité.
	 */
	public void setDistanceParcouru(long distanceParcouru) {
		this.distanceParcouru = distanceParcouru;
	}
}
