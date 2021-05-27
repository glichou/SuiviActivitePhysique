package modele;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import utilitaire.AsciiTable;

public class Statistique {
	private LocalDate debut;
	private LocalDate fin;
	private IndicateurStatistique<Duration> duree;
	private IndicateurStatistique<Long> distanceParcouru;
	
	public Statistique(LocalDate debut, LocalDate fin, IndicateurStatistique<Duration> duree, IndicateurStatistique<Long> distanceParcouru) {
		this.debut = debut;
		this.fin = fin;
		this.duree = duree;
		this.distanceParcouru = distanceParcouru;
	}
	
	public static Statistique genererStatistiques(LocalDate debutPeriode, LocalDate finPeriode, ArrayList<Activite> liste) {
		//Retirer toutes les activités qui commencent strictement avant le début de la période
		//et celles qui termines strictement après le jour de fin de la période.
		ArrayList<Activite> activites = (ArrayList<Activite>) liste.clone();
		
		activites.removeIf(activite -> 
				activite.getDebut().toLocalDate().isBefore(debutPeriode) ||
				activite.getDebut().toLocalDate().isAfter(finPeriode));
		
		if(activites.size() > 0) {
			//Instancier un indicateur pour les statistiques de durée et distance.
			IndicateurStatistique<Duration> duree = new IndicateurStatistique<>();
			IndicateurStatistique<Long> distanceParcouru = new IndicateurStatistique<>();
			
			//Parcourir tous les éléments pour remplir les statistiques.
			activites.forEach(activite -> {
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
			
			//Retourner les statistiques.
			return new Statistique(debutPeriode, finPeriode, duree, distanceParcouru);
		} else {
			//On retourne rien s'il n'y a pas de statistiques.
			return null;
		}
		
	}
	
	//Posibilité de faire un hasmap avec toutes les stats + clé => nom humain de l'objet
	
	//https://stackoverflow.com/questions/31657036/getting-object-with-max-date-property-from-list-of-objects-java-8 
	//Comparing java (Collection max min...)
	//Comparator.comparing(c -> c.lastUpdated));
	
	//Avoir total de temps d'activité dans le mois/semaine (total/moyenne par séance)
	//3 derniers mois (par mois)
	//changerPeriode(debut, fin)
	//getDureeMax, getMoyenneDuree, getDureeTotal
	//getDistancemax, getMoyenneDistance, getDistanceTotal
	//getDifficulteMoyenne, 
	//Avoir le nb de KM
	
	public LocalDate getDebut() {
		return debut;
	}

	public LocalDate getFin() {
		return fin;
	}

	public IndicateurStatistique<Duration> getDuree() {
		return duree;
	}

	public IndicateurStatistique<Long> getDistanceParcouru() {
		return distanceParcouru;
	}
	
	public String toStringPeriode() {
		DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		return "[" + this.debut.format(formatDate) + " au " + this.fin.format(formatDate) + "]";
	}
	
	public String toString() {
		String[] entete = {"", "Distance parcourue", "Durée entrainement"};
		String[][] contenu = {
		        {"Minimum", distanceParcouru.getMin() + "km", duree.getMin().toMinutes() + "min"},
		        {"Maximum", distanceParcouru.getMax() + "km", duree.getMax().toMinutes() + "min"},
		        {"Moyenne", distanceParcouru.getMoyenne() + "km", duree.getMoyenne().toMinutes() + "min"},
		        {"Total", distanceParcouru.getTotal() + "km", duree.getTotal().toMinutes() + "min"}};
		return toStringPeriode() + "\n\n" +
		        AsciiTable.getTable(entete, contenu);
	}
}
