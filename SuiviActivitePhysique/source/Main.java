import java.util.Scanner;

import controleur.ControleurApplication;
import modele.ModeleApplication;
import modele.Utilisateur;
import vue.VueApplication;

/**
 * Controleur de l'pplication de suivi d'activité physique.
 * @author lichou
 * @version 0.1
 */
public class Main {
	private static Scanner clavier;
	private static Utilisateur utilisateur;
	
	/**
	 * Point d'entrée de l'application de suivi d'activité physique.
	 * @param paramètres Les paramètres en entrée de l'application.
	 */
	public static void main(String[] parametres) {
		ModeleApplication modele = new ModeleApplication();
		VueApplication vue = new VueApplication();
		
		ControleurApplication controleur = new ControleurApplication(modele, vue);
		
		//Lancer l'application.
		vue.demarrer();
	}
}
