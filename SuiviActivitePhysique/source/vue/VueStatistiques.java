package vue;

import static java.time.temporal.TemporalAdjusters.firstDayOfMonth;
import static java.time.temporal.TemporalAdjusters.lastDayOfMonth;

import java.time.LocalDate;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.Locale;

import controleur.ControleurStatistiques;
import modele.Statistique;
import utilitaire.Clavier;

/**
 * Classe de la vue statistique.
 * @author Grégoire LICHOU
 */
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
					this.afficherStatistiques();
					break;
			}
			System.out.println();
		} while(numeroSaisi != 4);
	}
	
	/**
	 * Afficher les statistiques selon une période donnée par l'utlisateur.
	 */
	public void afficherStatistiques() {
		System.out.print("Période de début (format JJ/MM/AAAA): ");
		LocalDate debut = clavier.recupererDate();
		System.out.print("Période de fin (format JJ/MM/AAAA): ");
		LocalDate fin = clavier.recupererDate();
		System.out.println();
		
		if(debut.isBefore(fin) || debut.isEqual(fin)) {
			afficherStatistiques(debut, fin);
		} else {
			System.out.println("→ La période donné n'est pas valide ! La date de fin ne peut pas être avant la date de début.");
		}
	}
	
	/**
	 * Afficher les statistique pour une période donnée.
	 * @param debut
	 * @param fin
	 */
	public void afficherStatistiques(LocalDate debut, LocalDate fin) {
		Statistique statistique = this.controleur.getStatistiques(debut, fin);
		
		if(statistique != null) {
			System.out.println(statistique.toString());
		} else {
			System.out.println("→ Vous n'avez aucune activité pour cette période !");
		}
	}
	
}
