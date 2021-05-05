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
import utilitaire.Clavier;

/**
 * Classe vue
 * @author lichou
 */
public class VueActivite {
	private ControleurActivite controleur;	
	private Clavier clavier;
	
	/**
	 * Constructeur de la classe de VueActivite.
	 */
	public VueActivite() {
		//Créer un objet pour récupérer le texte saisit.
		clavier = new Clavier(System.in);
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
			numeroSaisi = clavier.recupererNombre(0, 4);
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
			index = clavier.recupererNombre(1, liste.size());
			
			Activite activite = liste.get(index - 1);
			
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
		//LocalDateTime date, Difficulte difficulte, Duration duree, long distance
		System.out.print("Jour (format JJ/MM/AAAA): ");
		LocalDate date = clavier.recupererDate(true);
		System.out.print("Heure (format HHhmm): ");
		LocalTime heure = clavier.recupererHeure(true);
		System.out.print("Durée (minutes): ");
		Duration duree = clavier.recupererDuree(1, 2880);
		System.out.print("Distance (km): ");
		long distance = clavier.recupererGrandNombre(1, 10000);
		Categorie categorie = this.controleur.afficherSelectionCategorie();
		System.out.println();
		
		//Assembler la date et l'heure de début pour former un DateTime.
		LocalDateTime dateDebut = date.atTime(heure);
		
		//Ajouter l'activité.
		this.controleur.ajouterUneActivite(dateDebut, Difficulte.CINQ, duree, distance, categorie);
	}
}
