package vue;

import java.util.ArrayList;
import java.util.Scanner;

import controleur.ControleurFavoris;
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
			System.out.println("4] Revenir à l'accueil");
			System.out.print("\nSaisir l'option voulue: ");
			
			numeroSaisi = recupererNumeroOption(4);
			
			switch(numeroSaisi) {
			case 1:
				break;
			case 2:
				break;
			case 3:
				break;
			}
			System.out.println();
		} 	while(numeroSaisi != 4);
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
	
	
	private int recupererNumeroOption(int max) {
		int numeroSaisi;
		boolean valide = false;
		
		do {
			while(!clavier.hasNextInt()) {
				System.out.print("Veuillez saisir une option valide ! Entrez votre numéro: ");
				clavier.next();
			}
			numeroSaisi = clavier.nextInt();
			if(numeroSaisi > 0 && numeroSaisi <=max) {
		}
	}while (!valide);
	return numeroSaisi;
	}
}
