package vue;

import java.time.LocalDate;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.Locale;

import static java.time.temporal.TemporalAdjusters.*;
import controleur.ControleurStatistiques;
import modele.Statistique;
import utilitaire.Clavier;

public class VueStatistiques {
	private ControleurStatistiques controleur;
	private Clavier clavier;
	
	
	/**
	* Constructeur de la classe de vue statistiques.
	*/
	public VueStatistiques() {
		clavier = new Clavier(System.in);
	}
	
	/**
	* Définir le contrôleur de cette vue.
	* @param controleur Le controleur de la vue.
	*/
	public void setControleur(ControleurStatistiques controleur) {
		this.controleur = controleur;
	}

	/**
	 * Afficher le menu principal de sélection des options
	 * pour les statistiques.
	 */
	public void afficherMenu() {
		int numeroSaisi;
		
		//Afficher le menu tant que l'utilisateur ne souhaite pas sortir.
		do {
			System.out.println("\t[STATISTIQUES]\n");
			System.out.println("1] Statistiques du mois");
			System.out.println("2] Statistiques de la semaine");
			System.out.println("3] Statistiques sur une période");
			System.out.println("4] Revenir à l'accueil");
			System.out.print("\nSaisir l'option voulue: ");
			
			//Récupérer la valeur saisie par l'utilisateur.
			numeroSaisi = clavier.recupererNombre(1, 4);
			System.out.println();
			
			//Executer l'action demandé par l'utilisateur.
			switch(numeroSaisi) {
				case 1: {
					LocalDate maintenant = LocalDate.now();
					LocalDate debut = maintenant.with(firstDayOfMonth());
					LocalDate fin = maintenant.with(lastDayOfMonth());
					
					this.afficherStatistiques(debut, fin);
					break;
				}
				case 2: {
					LocalDate maintenant = LocalDate.now();
					
					TemporalField champTemporel = WeekFields.of(Locale.getDefault()).dayOfWeek();

					LocalDate debut = maintenant.with(champTemporel, 1);
					LocalDate fin = debut.plusDays(6);
					
					this.afficherStatistiques(debut, fin);
					break;
				}
				case 3:
					//this.afficherUneActivite();
					break;
			}
			System.out.println();
		} while(numeroSaisi != 4);
	}
	
	public void afficherStatistiques(LocalDate debut, LocalDate fin) {
		Statistique statistique = this.controleur.getStatistiques(debut, fin);
		
		if(statistique != null) {
			System.out.println(statistique.toString());
		} else {
			System.out.println("→ Vous n'avez aucune activité pour cette période !");
		}
	}
	
}
