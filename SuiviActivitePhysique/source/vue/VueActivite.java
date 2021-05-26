package vue;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Optional;

import controleur.ControleurActivite;
import modele.Activite;
import modele.Categorie;
import modele.Difficulte;
import utilitaire.Clavier;

/**
 * Classe de la vue activité
 * @author Grégoire LICHOU
 * @author Quentin COUSTURIAN
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
			System.out.println("4] Modifier une activité");
			System.out.println("5] Supprimer une activité");
			System.out.println("6] Revenir à l'accueil");
			System.out.print("\nSaisir l'option voulue: ");
			
			//Récupérer la valeur saisie par l'utilisateur.
			numeroSaisi = clavier.recupererNombre(0, 6);
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
				case 4:
					this.modifierUneActivite();
					break;
				case 5:
					this.supprimerUneActivite();
					break;
			}
		} while(numeroSaisi != 6);
	}
	
	
	public void modifierUneActivite() {
		//Demander l'identifiant de l'activité si la liste n'est pas vide.
		int index = 0;
		if(this.controleur.recupererNbActivites() > 0) {
			System.out.print("Veuillez saisir l'identifiant de l'activité : ");
			index = clavier.recupererNombre(1, this.controleur.recupererNbActivites());
			
			try {
				Activite activite = this.controleur.recupererActivite(index - 1);
				
				System.out.println("Veuillez modifier les informations de l'activité : \n");
				
				System.out.print("Jour (format JJ/MM/AAAA): ");
				LocalDate date = clavier.recupererDate(Optional.of(activite.getDebut().toLocalDate()));
				System.out.print("Heure (format HHhmm): ");
				LocalTime heure = clavier.recupererHeure(Optional.of(activite.getDebut().toLocalTime()));
				LocalDateTime dateDebut = date.atTime(heure);
				activite.setDebut(dateDebut);
				
				
				System.out.print("Durée (minutes): ");
				Duration duree = clavier.recupererDuree(1, 2880, Optional.of(activite.getDuree()));
				activite.setDuree(duree);
				
				System.out.print("Distance (km): ");
				long distance = clavier.recupererGrandNombre(0, 10000, Optional.of(activite.getDistanceParcouru()));
				activite.setDistanceParcouru(distance);
				
				System.out.print("Catégorie: ");
				Categorie categorie = this.controleur.afficherSelectionCategorie(activite.getCategorie());
				activite.setCategorie(categorie);
				
				System.out.print("Difficulté: ");
				Difficulte difficulte = clavier.recupererDifficulte(Optional.of(activite.getDifficulte()));
				activite.setDifficulte(difficulte);
				
				System.out.println();
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("L'activité selectionné n'existe pas !"+ e.getLocalizedMessage());
			}
			
		} else {
			System.out.println("→ Vous n'avez encore aucune activité pour le moment !");
		}
		System.out.println();
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
		
		//Demander l'identifiant de l'activité si la liste n'est pas vide.
		int index = 0;
		if(liste.size() > 0) {
			System.out.print("Veuillez saisir l'identifiant de l'activité : ");
			index = clavier.recupererNombre(1, liste.size());
			
			Activite activite = liste.get(index - 1);
			
			//Afficher les informations sur l'activité.
			System.out.println(activite);
			
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
		LocalDate date = clavier.recupererDate(Optional.of(LocalDate.now()));
		System.out.print("Heure (format HHhmm): ");
		LocalTime heure = clavier.recupererHeure(Optional.of(LocalTime.now()));
		System.out.print("Durée (minutes): ");
		Duration duree = clavier.recupererDuree(0, 2880);
		System.out.print("Distance (km): ");
		long distance = clavier.recupererGrandNombre(0, 10000);
		Categorie categorie = this.controleur.afficherSelectionCategorie();
		System.out.print("Difficulté: ");
		Difficulte difficulte = clavier.recupererDifficulte();
		System.out.println();
		
		//Assembler la date et l'heure de début pour former un DateTime.
		LocalDateTime dateDebut = date.atTime(heure);
		
		//Ajouter l'activité.
		this.controleur.ajouterUneActivite(dateDebut, difficulte, duree, distance, categorie);
	}
	
	public void supprimerUneActivite() {
		//Demander l'identifiant de l'activité si la liste n'est pas vide.
		int index = 0;
		if(this.controleur.recupererNbActivites() > 0) {
			System.out.print("Veuillez saisir l'identifiant de l'activité : ");
			index = clavier.recupererNombre(1, this.controleur.recupererNbActivites());
			
			try {
				this.controleur.supprimerActivite(index - 1);
				
				System.out.println("Veuillez saisir les informations de l'activité: \n");
			} catch (Exception e) {
				System.out.println("L'activité selectionné a bien été supprimée !");
			}
			
		} else {
			System.out.println("→ Vous n'avez encore aucune activité pour le moment !");
		}
		System.out.println();
	}
}
