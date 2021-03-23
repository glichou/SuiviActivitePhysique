package vue;

import java.util.ArrayList;
import java.util.Scanner;

import controleur.ControleurActivite;
import modele.Activite;

public class VueActivite {
	private ControleurActivite controleur;	
	private Scanner clavier;
	
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
	
	public void afficherMenu() {
		int numeroSaisi;
		
		//Afficher le menu tant que l'utilisateur ne souhaite pas sortir.
		do {
			System.out.println("\t[ACTIVITÉ]");
			System.out.println("1] Afficher les activités");
			System.out.println("2] Afficher une activité");
			System.out.println("3] Ajouter une activité");
			System.out.println("4] Revenir à l'accueil");
			System.out.print("\nSaisir l'option voulue: ");
			
			//Récupérer la valeur saisie par l'utilisateur.
			numeroSaisi = recupererNumeroOption(4);
			
			// Executer l'action demandé par l'utilisateur.
			switch(numeroSaisi) {
				case 1:
					//Faire l'option 1
					break;
				case 2:
					//faire l'option 2.
					break;
				case 3:
					//Faire l'option 3.
					break;
			}
			System.out.println();
		} while(numeroSaisi != 4);
	}
	
	/**
	 * Afficher l'ensemble des activités de l'utilisateurs.
	 */
	public void afficherLesActivites() {
		ArrayList<Activite> liste = this.controleur.recupererActivites();
		
		int compteur = 0;
		System.out.println("Voici la liste de vos précédentes activités :");
		for(Activite activite : liste){
			compteur++;
			System.out.println(compteur + "] " + activite.getClass());
		}
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
			}
		} while (!valide);
		
		return numeroSaisi;
	}
}
