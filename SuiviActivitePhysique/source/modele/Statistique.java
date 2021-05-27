package modele;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import utilitaire.AsciiTable;

/**
 * Classe permettant de représenter des Statistiques.
 * @author Grégoire LICHOU
 * @version 0.1
 */
public class Statistique {
	private LocalDate debut;
	private LocalDate fin;
	private IndicateurStatistique<Duration> duree;
	private IndicateurStatistique<Long> distanceParcouru;
	
	/**
	 * Constructeur de la classe Statistique.
	 * @param debut La date de début de la période.
	 * @param fin La date de fin de la période.
	 * @param duree Les indicateurs sur la durées des activités.
	 * @param distanceParcouru Les indicateur sur la distance parcourue lors des activités.
	 */
	public Statistique(LocalDate debut, LocalDate fin, IndicateurStatistique<Duration> duree, IndicateurStatistique<Long> distanceParcouru) {
		this.debut = debut;
		this.fin = fin;
		this.duree = duree;
		this.distanceParcouru = distanceParcouru;
	}
	
	/**
	 * Générer des statistiques à partir d'une liste d'activité et d'une période.
	 * @param debutPeriode La date de début de la période.
	 * @param finPeriode La date de fin de la période.
	 * @param liste La liste des activités à utiliser.
	 * @return Les statistiques s'il y a eu des activités pendant cette période.
	 */
	public static Statistique genererStatistiques(LocalDate debutPeriode, LocalDate finPeriode, ArrayList<Activite> liste) {
		//Cloner la liste pour ne pas modifier celle d'origine.
		@SuppressWarnings("unchecked")
		ArrayList<Activite> activites = (ArrayList<Activite>) liste.clone();
		
		//Retirer toutes les activités qui commencent strictement avant le début de la période
		//et celles qui termines strictement après le jour de fin de la période.
		activites.removeIf(activite -> 
				activite.getDebut().toLocalDate().isBefore(debutPeriode) ||
				activite.getDebut().toLocalDate().isAfter(finPeriode));
		
		if(activites.size() > 0) {
			//Instancier un indicateur pour les statistiques de durée et distance.
			IndicateurStatistique<Duration> duree = new IndicateurStatistique<>();
			IndicateurStatistique<Long> distanceParcouru = new IndicateurStatistique<>();
			
			//Parcourir toutes les activités.
			activites.forEach(activite -> {
				
				//Ajouter une nouvelle durée à l'indicateur pour le mettre à jour.
				duree.ajouterElement(activite.getDuree(), new CalculateurStatistique<Duration>() {
					@Override
					public Duration calculerTotal(Duration total, Duration element) {
						return total.plus(element);
					}
					@Override
					public Duration calculerMoyenne(Duration total, int nbElement) {
						return total.dividedBy(nbElement);
					}
				});
				
				//Ajouter une nouvelle distance à l'indicateur pour le mettre à jour.
				distanceParcouru.ajouterElement(activite.getDistanceParcouru(), new CalculateurStatistique<Long>() {
					@Override
					public Long calculerTotal(Long total, Long element) {
						return total + element;
					}
					@Override
					public Long calculerMoyenne(Long total, int nbElement) {
						return total / nbElement;
					}
					
				});
			});
			return new Statistique(debutPeriode, finPeriode, duree, distanceParcouru);
		} else {
			return null;
		}
		
	}
	
	/**
	 * Récupérer la date de début de prise en compte des activités
	 * dans les statistiques.
	 * @return La date de début.
	 */
	public LocalDate getDebut() {
		return debut;
	}

	/**
	 * Récupérer la date de fin de prise en compte des activités
	 * dans les statistiques.
	 * @return La date de fin.
	 */
	public LocalDate getFin() {
		return fin;
	}
	
	/**
	 * Récupérer les indicateurs pour les statistiques de durée.
	 * @return L'indicateurs de statistiques pour la durée.
	 */
	public IndicateurStatistique<Duration> getDuree() {
		return duree;
	}
	
	/**
	 * Récupérer les indicateurs pour les statistiques de la 
	 * distance parcourue.
	 * @return L'indicateur de statistique pour la distance.
	 */
	public IndicateurStatistique<Long> getDistanceParcouru() {
		return distanceParcouru;
	}
	
	/**
	 * Récupérer une chaine joliement formaté de la période de
	 * début et fin des statistiques.
	 * @return La chaine de caractères.
	 */
	public String toStringPeriode() {
		DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		return "[" + this.debut.format(formatDate) + " au " + this.fin.format(formatDate) + "]";
	}
	
	/**
	 * Récupérer les statistiques des activités joliment formaté
	 * dans une chaine de carcatères.
	 */
	@Override
	public String toString() {
		String[] entete = {"", "Distance parcourue", "Durée entrainement"};
		String[][] contenu = {
		        {"Minimum", distanceParcouru.getMin() + "km", duree.getMin().toMinutes() + "min"},
		        {"Maximum", distanceParcouru.getMax() + "km", duree.getMax().toMinutes() + "min"},
		        {"Moyenne", distanceParcouru.getMoyenne() + "km", duree.getMoyenne().toMinutes() + "min"},
		        {"Total", distanceParcouru.getTotal() + "km", duree.getTotal().toMinutes() + "min"}};
		return toStringPeriode() + "\n\n" + AsciiTable.getTable(entete, contenu);
	}
}
