package vue;

import java.time.Duration;
import java.util.ArrayList;

import controleur.ControleurFavoris;
import modele.Categorie;
import modele.Difficulte;
import modele.Favoris;
import utilitaire.Clavier;


public class VueFavoris {
	private ControleurFavoris controleur;
	private Clavier clavier;
	
	
	public VueFavoris() {
		clavier = new Clavier(System.in);
	}

	public void setControleur(ControleurFavoris controleur) {
		this.controleur = controleur;
	}
	
	public void afficherMenu() {
		int numeroSaisi;
		do {
			System.out.println("\t[FAVORIS]\n");
			System.out.println("1] Afficher les favoris");
			System.out.println("2] Afficher un favori");
			System.out.println("3] Ajouter un favori");
			System.out.println("4] Supprimer un favori");
			System.out.println("5] Revenir à l'accueil");
			System.out.print("\nSaisir l'option voulue: ");
			
			numeroSaisi = clavier.recupererNombre(1, 5);
			System.out.println();

			//Executer l'action demandé par l'utilisateur.
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
				case 4:
					this.supprimerUnFavori();
					break;
			}
		} while(numeroSaisi != 5);
	}
	
	private void supprimerUnFavori() {		
		int index = 0;
		if(this.controleur.recupererNbFavoris() > 0) {
			
			System.out.print("Veuillez saisir l'identifiant du favori : ");
			index = clavier.recupererNombre(1, this.controleur.recupererNbFavoris());
			
			try {
				this.controleur.supprimerFavoris(index - 1);
				System.out.println("Le favoris selectionné a bien été supprimé !");
			} catch (Exception e) {
				System.out.println("Le favoris selectionné n'existe pas !");
			}
			
		} else {
			System.out.println("→ Vous n'avez encore aucun favori pour le moment !");
		}
		System.out.println();
	}

	public void afficherUnFavori() {
		int index = 0;
		if(this.controleur.recupererNbFavoris() > 0) {
			System.out.print("Veuillez saisir l'identifiant du favori : ");
			
			index = clavier.recupererNombre(1, this.controleur.recupererNbFavoris());
			try {
				System.out.println(this.controleur.recupererFavoris(index - 1));
			} catch (Exception e) {
				System.out.println("Le favoris selectionné n'existe pas !");
			}
		} else {
			System.out.println("→ Vous n'avez encore aucun favori pour le moment !");
		}
		System.out.println();
	}
	
	
	public void afficherLesFavoris() {
		ArrayList<Favoris> liste = this.controleur.recupererFavoris();
		
		if(liste.size() > 0) {
			System.out.println("Voici la liste de vos favoris :");
			for(int i = 0; i < liste.size(); i++) {
				System.out.println((i + 1) + "] " + liste.get(i).toSmallString());
			}
		} else {
			System.out.println("→ Vous n'avez encore aucun favoris pour le moment !");
		}
		System.out.println();
	}

	
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
		
		this.controleur.ajouterUnFavori(titre, lien, duree, categorie, memo);
	}
}
