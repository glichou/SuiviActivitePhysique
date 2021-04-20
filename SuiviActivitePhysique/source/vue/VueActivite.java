package vue;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

import controleur.ControleurActivite;
import modele.Activite;
import modele.Categorie;
import modele.Difficulte;

/**
 * Classe vue
 * @author lichou
 */
public class VueActivite {
	private ControleurActivite controleur;	
	private Scanner clavier;
	
	/**
	 * Constructeur de la classe de VueActivite.
	 */
	public VueActivite() {
		//Créer un objet pour récupérer le texte saisit.
		clavier = new Scanner(System.in);
	}
	
	
	/**
	 * Définir le controleur en charge de cette vue.
	 * @param controleur Le controleur en charge de la vue.
	 */
	public void setControleur(ControleurActivite controleur) {
		this.controleur = controleur;	
	}
	
	/**
	 * Afficher le menu principal de sélection des options
	 * pour les activités.
	 */
	public void afficherMenu() {
		int numeroSaisi;
		
		//Afficher le menu tant que l'utilisateur ne souhaite pas sortir.
		do {
			System.out.println("\t[ACTIVITÉ]\n");
			System.out.println("1] Afficher les activités");
			System.out.println("2] Afficher une activité");
			System.out.println("3] Ajouter une activité");
			System.out.println("4] Revenir à l'accueil");
			System.out.print("\nSaisir l'option voulue: ");
			
			//Récupérer la valeur saisie par l'utilisateur.
			numeroSaisi = recupererNumeroOption(4);
			System.out.println();
			
			//Executer l'action demandé par l'utilisateur.
			switch(numeroSaisi) {
				case 1:
					this.afficherLesActivites();
					break;
				case 2:
					this.afficherUneActivite();
					break;
				case 3:
					this.ajouterUneActivite();
					break;
			}
		} while(numeroSaisi != 4);
	}
	
	/**
	 * Afficher l'ensemble des activités de l'utilisateurs.
	 */
	public void afficherLesActivites() {
		//Récupérer les activités dans le profil de l'utilisateur.
		ArrayList<Activite> liste = this.controleur.recupererActivites();
		
		//Afficher un message d'accueil.
		System.out.println("Voici la liste de vos précédentes activités :");
		
		//Lister toutes les activités de l'utilisateur avec un identifiant s'il y en a.
		if(liste.size() > 0) {
			for(int index = 0; index < liste.size(); index++) {
				System.out.println((index + 1) + "] "  + liste.get(index).toSmallString());
			}
		} else {
			//Afficher un message car il n'y a pas d'activité dans la liste.
			System.out.println("→ Vous n'avez encore aucune activité pour le moment !");
		}
		System.out.println();
	}
	
	/**
	 * Afficher l'ensemble des activités de l'utilisateurs.
	 */
	public void afficherUneActivite() {
		//Récupérer l'ensembles des activités de l'utilisateur.
		ArrayList<Activite> liste = this.controleur.recupererActivites();
		
		//Demander l'identifiant de l'activité si la lise n'est pas vide.
		int index = 0;
		if(liste.size() > 0) {
			System.out.print("Veuillez saisir l'identifiant de l'activité : ");
			
			//Récupérer un index du tableau valide.
			boolean valide = false;
			int numeroSaisi = 0;
			do {			
				if(!clavier.hasNextInt()) {
					System.out.print("Veuillez saisir un nombre : ");
				} else {
					numeroSaisi = clavier.nextInt();
					
					if(numeroSaisi > 0 && numeroSaisi <= liste.size()) {
						index = numeroSaisi - 1;
						valide = true;
					} else {
						System.out.print("Veuillez une activité existante : ");
					}
				}
			} while(!valide);
			
			//Récupérer l'activité dans la liste.
			Activite activite = liste.get(index);
			
			//Afficher les informations sur l'activité.
			System.out.println(activite);
			System.out.println();
			
		} else {
			System.out.println("→ Vous n'avez encore aucune activité pour le moment !");
		}
		System.out.println();
	}
	
	/**
	 * Récupérer les informations pour ajouter une activité
	 * dans le profil de l'utilisateur.
	 */
	public void ajouterUneActivite() {
		System.out.println("Veuillez saisir les informations de l'activité: \n");
		clavier.nextLine();
		//LocalDateTime date, Difficulte difficulte, Duration duree, long distance
		System.out.print("Jour (format JJ/MM/AAAA): ");
		LocalDate date = recupererDate();
		System.out.print("Heure (format HHhmm): ");
		LocalTime heure = recupererHeure();
		System.out.print("Durée (minutes): ");
		Duration duree = recupererDuree();
		System.out.print("Distance (km): ");
		long distance = recupererDistance();
		Categorie categorie = this.controleur.afficherSelectionCategorie();
		System.out.println();
		
		//Assembler la date et l'heure de début pour former un DateTime.
		LocalDateTime dateDebut = date.atTime(heure);
		
		//Ajouter l'activité.
		this.controleur.ajouterUneActivite(dateDebut, Difficulte.CINQ, duree, distance, categorie);
	}
	
	/**
	 * Récupérer la distance parcourue saisit par l'utilisateur.
	 * @return La distance parcouru.
	 */
	private long recupererDistance() {
		boolean valide = false;
		long distance = 0;
		
		do {			
			//Vérifier si on a bien un nombre en entrée.
			if(!clavier.hasNextLong()) {
				System.out.print("Distance non valide. Essayez à nouveau : ");

			} else {
				distance = clavier.nextLong();
				valide = true;
			}
		} while(!valide);
		
		return distance;
	}

	/**
	 * Récupérer l'heure saisit par l'utilisateur.
	 * @return L'heure saisit par l'utilisateur.
	 */
	private LocalTime recupererHeure() {
		DateTimeFormatter format = DateTimeFormatter.ofPattern("H'h'mm");
		LocalTime heure = null;
		boolean valide = false;
		String chaine;

		do {
			chaine = clavier.nextLine().trim();
			
			//Enregistrer l'heure actuelle si champ vide.
			if(chaine.length() < 1 ) {
				heure = LocalTime.now();
				System.out.println("L'heure actuelle " + heure.format(format) + " a été selectionné.");
				valide = true;
			
			//Vérifier que la date semble viable.
			} else if (!chaine.matches("(0[0-9]|1[0-9]|2[0-3])h([0-5][0-9])")) {
				System.out.print("Format d'heure non valide. Essayez à nouveau : ");
				
			//La date est semble bonne, essayer de l'enregistrer.
			} else {
				try {
					heure = LocalTime.parse(chaine, format);
					valide = true;
				} catch(DateTimeParseException exception) {
					System.out.print("Il y a une erreur dans la date. Essayez à nouveau : ");
				}
			}
		} while(!valide);
		
		return heure;
	}
	
	/**
	 * Récupérer une date valide saisie par l'utilisateur.
	 * @return La date saisie par l'utilisateur.
	 */
	private LocalDate recupererDate() {
		DateTimeFormatter format = DateTimeFormatter.ofPattern("dd'/'MM'/'yyyy");
		LocalDate date = null;
		boolean valide = false;
		String chaine;

		do {
			chaine = clavier.nextLine().trim();
			
			//Récupérer et selectionner la date du jour si vide.
			if(chaine.length() < 1 ) {
				date = LocalDate.now();
				System.out.println("La date actuelle " + date.format(format) + " a été selectionné.");
				valide = true;
			
			//Vérifier que la date semble viable et l'enregistrer dans ce cas.
			} else {
				try {
					date = LocalDate.parse(chaine, format);
					valide = true;
				} catch(DateTimeParseException exception) {
					System.out.print("Il y a une erreur dans la date. Essayez à nouveau : ");
				}
			}
		} while(!valide);
		
		return date;
	}
	
	
	
	/**
	 * Récupérer une date valide saisie par l'utilisateur.
	 * @return La date saisie par l'utilisateur.
	 */
	private Duration recupererDuree() {
		Duration duree = null;
		boolean valide = false;
		long valeur;
		
		do {			
			//Vérifier si on a bien un nombre en entrée.
			if(!clavier.hasNextLong()) {
				System.out.print("Durée non valide. Essayez à nouveau : ");
			} else {
				valeur = clavier.nextLong();
				
				//Vérifier que la valeur semble valable.
				if(valeur > 0 && valeur <= 2880) {
					duree = Duration.ofMinutes(valeur);
					valide = true;
				} else {
					System.out.print("Durée invalide ! Essayez à nouveau : ");
				}
			}
		} while(!valide);
		
		return duree;
	}
	
	/**
	 * Récupérer un numéro d'option valide saisit par l'utilisateur.
	 * @param max Le numéro de la dernière option.
	 * @return Le numéro saisit par l'utilisateur.
	 */
	private int recupererNumeroOption(int max) {
		int numeroSaisi;
		boolean valide = false;
		
		do {
			while(!clavier.hasNextInt()) {
				System.out.print("Veuillez saisir une option valide ! Entrez votre numéro: ");
				clavier.next();
			}
			numeroSaisi = clavier.nextInt();
			if(numeroSaisi > 0 && numeroSaisi <= max) {
				valide = true;
			} else {
				System.out.print("Veuillez saisir une option valide ! Entrez votre numéro: ");
			}
		} while (!valide);
		
		return numeroSaisi;
	}
}
