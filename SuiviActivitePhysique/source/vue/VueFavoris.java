package vue;

import java.time.Duration;
import java.util.ArrayList;

import controleur.ControleurFavoris;
import modele.Categorie;
import modele.Difficulte;
import modele.Favoris;
import utilitaire.Clavier;

	/**
	* Classe vueFavoris
	* @author quent
	*/
public class VueFavoris {
	private ControleurFavoris controleur;
	private Clavier clavier;
	
	/**
	* Constructeur de la classe de VueFavoris.
	*/
	public VueFavoris() {
		clavier = new Clavier(System.in);
	}
	
	
	/**
	* Définir le contrôleur de cette vue.
	* @param controleur Le controleur de la vue.
	*/
	public void setControleur(ControleurFavoris controleur) {
		this.controleur = controleur;
	}
	
	
	 /**
	 * Afficher le menu principal de sélection des options pour les favoris.
	 */
	public void afficherMenu() {
		int numeroSaisi;
		do {
			System.out.println("\t[FAVORIS]\n");
			System.out.println("1] Afficher les favoris");
			System.out.println("2] Afficher un favori");
			System.out.println("3] Ajouter un favori");
			System.out.println("4] Revenir à l'accueil");
			System.out.print("\nSaisir l'option voulue: ");
			
			//Récupérer la valeur saisie par l'utilisateur.
			numeroSaisi = clavier.recupererNombre(1, 4);
			System.out.println();

			//Executer l'action demandée par l'utilisateur.
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
	
	/**
	 * Afficher l'ensemble des activités de l'utilisateurs.
	 */	
	public void afficherUnFavori() {
		//Récupérer l'ensembles des favoris de l'utilisateur.
		ArrayList<Favoris> liste = this.controleur.recupererFavoris();
		
		//Demander l'identifiant des favoris si la liste n'est pas vide.
		int index = 0;
		if(liste.size() > 0) {
			System.out.print("Veuillez saisir l'identifiant du favori : ");
			index = clavier.recupererNombre(1, liste.size());
			
			Favoris favoris = liste.get(index - 1);
			
			//Afficher les informations sur le favori.
			System.out.println(favoris);
			
		} else {
			System.out.println("→ Vous n'avez encore aucun favori pour le moment !");
		}
		System.out.println();
	}
	
	
	//Afficher l'ensemble des favoris de l'utilisateurs.
	public void afficherLesFavoris() {
		
		//Récupérer les favoris dans le profil de l'utilisateur.
		ArrayList<Favoris> liste = this.controleur.recupererFavoris();
		
		//Lister tous les favoris de l'utilisateur avec un identifiant s'il y en a.
		if(liste.size() > 0) {
			System.out.println("Voici la liste de vos favoris :");
			for(int i = 0; i < liste.size(); i++) {
				System.out.println((i + 1) + "] " + liste.get(i).toSmallString());
			}
		} else {
			//Afficher un message car il n'y a pas de favoris dans la liste.
			System.out.println("→ Vous n'avez encore aucun favoris pour le moment !");
		}
		System.out.println();
	}
	
	

	 // Récupérer les informations pour ajouter un favori dans le profil de l'utilisateur.
	public void ajouterUnFavori() {
		System.out.println(" [AJOUT D'UN FAVORI]\n");
		
		System.out.print("Titre: ");
		String titre = clavier.recupererTexteCourt(true, true);
		
		System.out.print("URL: ");
		String lien = clavier.recupererURL();
		
		
		System.out.print("Durée (en minutes): ");
		Duration duree = clavier.recupererDuree(0, 2880);
		
		Categorie categorie = this.controleur.afficherSelectionCategorie();
		
		System.out.println("Mémo: ");
		String memo = clavier.recupererTexteLong();
		
		// Ajoute le favori
		this.controleur.ajouterUnFavori(titre, lien, duree, categorie, memo);
	}
}
