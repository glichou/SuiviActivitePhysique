package vue;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

import controleur.ControleurFavoris;
import modele.Categorie;
import modele.Difficulte;
import modele.Favoris;


public class VueFavoris {
	private ControleurFavoris controleur;
	private Scanner clavier;
	
	
	public VueFavoris() {
		clavier = new Scanner(System.in);
	}

	
	
	public void setControleur(ControleurFavoris controleur) {
		this.controleur = controleur;
	}
	
	public void afficherMenu() {
		int numeroSaisi;
		
		do {
			System.out.println("\t[FAVORIS]");
			System.out.println("1] Afficher les favoris");
			System.out.println("2] Afficher un favori");
			System.out.println("3] Ajouter un favori");
			System.out.println("4] Revenir √† l'accueil");
			System.out.print("\nSaisir l'option voulue: ");
			
			numeroSaisi = recupererNumeroOption(4);
			System.out.println();
			
			//Executer l'action demand√© par l'utilisateur.
			switch(numeroSaisi) {
				case 1:
					this.afficherLesFavoris();
					break;
				case 2:
					this.afficherUnFavori();
					break;
				case 3:
					this.ajouterUnFavori();
					break;
			}
		} while(numeroSaisi != 4);
	}
	
	public void afficherUnFavori() {
		ArrayList<Favoris> liste = this.controleur.recupererFavoris();
		
		int index = 0;
		if(liste.size() > 0) {
			System.out.print("Veuillez saisir l'identifiant du favori : ");
			
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
						System.out.print("Veuillez un favori existant : ");
					}
				}
			} while(!valide);
			
			Favoris favoris = liste.get(index);
			
			System.out.println(favoris);
			System.out.println();
			
		} else {
			System.out.println("‚Üí Vous n'avez encore aucun favori pour le moment !");
		}
		System.out.println();
	}
	
	
	public void afficherLesFavoris() {
		ArrayList<Favoris> liste = this.controleur.recupererFavoris();
		
		int compteur = 0;
		System.out.println("Voici la liste de vos favoris :");
		for(Favoris favoris : liste) {
			compteur++;
			System.out.println(compteur + "] " + favoris.getClass());
		}
	}
	
	
	
	public void ajouterUnFavori() {
		System.out.println("Veuillez saisir les informations du favori: \n");
		clavier.nextLine();
		
		System.out.print("Jour (format JJ/MM/AAAA): ");
		LocalDate date = recupererDate();
		
		System.out.print("Heure (format HHhmm): ");
		LocalTime heure = recupererHeure();
		
		// System.out.println("Veuillez saisir le lien du favori \n");
		// String lien = recupererLien();
		
		
		System.out.print("DurÈe (minutes): ");
		Duration duree = recupererDuree();
		
		Categorie categorie = this.controleur.afficherSelectionCategorie();
		System.out.println();
		
		// System.out.println("Veuillez saisir un mÈmo pour ce favori");
		// String memo = recupererMemo();
		
		
		LocalDateTime dateDebut = date.atTime(heure);
		
		this.controleur.ajouterUnFavori(dateDebut, lien, duree, categorie, memo);
	}
	
	private Duration recupererDuree() {
		Duration duree = null;
		boolean valide = false;
		long valeur;
		
		do {			
			
			if(!clavier.hasNextLong()) {
				System.out.print("DurÈe non valide. Essayez ‡ nouveau : ");
			} else {
				valeur = clavier.nextLong();
				
				
				if(valeur > 0 && valeur <= 2880) {
					duree = Duration.ofMinutes(valeur);
					valide = true;
				} else {
					System.out.print("DurÈe invalide ! Essayez ‡ nouveau : ");
				}
			}
		} while(!valide);
		
		return duree;
	}
	
	
	private LocalDate recupererDate() {
		DateTimeFormatter format = DateTimeFormatter.ofPattern("dd'/'MM'/'yyyy");
		LocalDate date = null;
		boolean valide = false;
		String chaine;

		do {
			chaine = clavier.nextLine().trim();
			
			if(chaine.length() < 1 ) {
				date = LocalDate.now();
				System.out.println("La date actuelle " + date.format(format) + " a ÈtÈ selectionnÈe.");
				valide = true;
			
			} else {
				try {
					date = LocalDate.parse(chaine, format);
					valide = true;
				} catch(DateTimeParseException exception) {
					System.out.print("Il y a une erreur dans la date. Essayez ‡ nouveau : ");
				}
			}
		} while(!valide);
		
		return date;
	}


	private LocalTime recupererHeure() {
		DateTimeFormatter format = DateTimeFormatter.ofPattern("H'h'mm");
		LocalTime heure = null;
		boolean valide = false;
		String chaine;

		do {
			chaine = clavier.nextLine().trim();
			
			if(chaine.length() < 1 ) {
				heure = LocalTime.now();
				System.out.println("L'heure actuelle " + heure.format(format) + " a ÈtÈ selectionnÈe.");
				valide = true;
			
			} else if (!chaine.matches("(0[0-9]|1[0-9]|2[0-3])h([0-5][0-9])")) {
				System.out.print("Format d'heure non valide. Essayez ‡ nouveau : ");
				
			} else {
				try {
					heure = LocalTime.parse(chaine, format);
					valide = true;
				} catch(DateTimeParseException exception) {
					System.out.print("Il y a une erreur dans la date. Essayez ‡ nouveau : ");
				}
			}
		} while(!valide);
		
		return heure;
	}

	private int recupererNumeroOption(int max) {
		int numeroSaisi;
		boolean valide = false;
		
		do {
			while(!clavier.hasNextInt()) {
				System.out.print("Veuillez saisir une option valide ! Entrez votre num√©ro: ");
				clavier.next();
			}
			numeroSaisi = clavier.nextInt();
			if(numeroSaisi > 0 && numeroSaisi <= max) {
				valide = true;
			} else {
				System.out.print("Veuillez saisir une option valide ! Entrez votre num√©ro: ");
			}
		} while (!valide);
		
		return numeroSaisi;
	}
}
