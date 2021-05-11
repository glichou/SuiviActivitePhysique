import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import controleur.ControleurApplication;
import modele.ModeleApplication;
import modele.Utilisateur;
import utilitaire.Clavier;
import vue.VueApplication;

/**
 * Controleur de l'pplication de suivi d'activité physique.
 * @author lichou
 * @version 0.1
 */
public class Main {
	private final static String FICHIER_SAUVEGARDE = "profil.sauvegarde";
	
	/**
	 * Point d'entrée de l'application de suivi d'activité physique.
	 * @param parametres Les paramètres en entrée de l'application.
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 * @throws ClassNotFoundException 
	 */
	public static void main(String[] parametres) throws FileNotFoundException, IOException, ClassNotFoundException {
		//Clavier.effacerEcran();
		//System.out.println(System.getProperty("os.name"));
		
		File fichier =  new File(FICHIER_SAUVEGARDE);
		
		//Mettre en place l'application.
		ModeleApplication modele = new ModeleApplication();
		VueApplication vue = new VueApplication();
		ControleurApplication controleur = new ControleurApplication(modele, vue);
		
		//Récupérer les données si un fichier de sauvegarde du profil existe.
		if(fichier.exists() && fichier.canRead()) {
			ObjectInputStream flux =  new ObjectInputStream(new FileInputStream(fichier));
			Utilisateur utilisateur = (Utilisateur) flux.readObject();
			
			if(utilisateur != null) {
				modele.setUtilisateur(utilisateur);
			}
			flux.close();
		}
		
		//Lancer l'application.
		vue.demarrer();
		
		//Créer une sauvegarde à la fermeture de l'application.
		fichier.createNewFile();
		if(fichier.exists() && fichier.canWrite()) {
			ObjectOutputStream flux =  new ObjectOutputStream(new FileOutputStream(fichier));
			flux.writeObject(modele.getUtilisateur());
			flux.close();
		}
	}
}
