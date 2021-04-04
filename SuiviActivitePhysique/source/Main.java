import controleur.ControleurApplication;
import modele.ModeleApplication;
import vue.VueApplication;

/**
 * Controleur de l'pplication de suivi d'activité physique.
 * @author lichou
 * @version 0.1
 */
public class Main {
	
	/**
	 * Point d'entrée de l'application de suivi d'activité physique.
	 * @param paramètres Les paramètres en entrée de l'application.
	 */
	public static void main(String[] parametres) {
		
		//Mettre en place l'application.
		ModeleApplication modele = new ModeleApplication();
		VueApplication vue = new VueApplication();
		ControleurApplication controleur = new ControleurApplication(modele, vue);
		
		//Lancer l'application.
		vue.demarrer();
	}
}
