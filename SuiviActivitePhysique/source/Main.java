import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import controleur.ControleurPrincipal;
import modele.ModeleApplication;
import modele.Utilisateur;
import vue.VuePrincipale;

/**
 * Controleur de l'application de suivi d'activité physique.
 * @author Grégoire LICHOU
 * @author Quentin COUSTURIAN
 * @version 0.1
 */
public class Main {
	private final static String FICHIER_SAUVEGARDE = "profil.sauvegarde";
	
	/**
	 * Point d'entrée de l'application de suivi d'activité physique.
	 * @param parametres Les paramètres en entrée de l'application.
	 */
	public static void main(String[] parametres) {		
		
		//Mettre en place l'application.
		ModeleApplication modele = new ModeleApplication();
		VuePrincipale vue = new VuePrincipale();
		new ControleurPrincipal(modele, vue);
		
		//Charger la sauvegarde dans le profil.
		modele.setUtilisateur(chargerProfil(FICHIER_SAUVEGARDE));
		
		//Lancer l'application.
		vue.demarrer();
		
		//Créer une sauvegarde à la fermeture de l'application.
		sauvegarderProfil(FICHIER_SAUVEGARDE, modele.getUtilisateur());
	}
	
	/**
	 * Récupérer le profil de l'utilisateur si un fichier de sauvegarde existe.
	 * @param repertoire Le répertoire du fichier de sauvegarde à récupérer.
	 * @return Le profil de l'utilisateur sauvegardé.
	 */
	private static Utilisateur chargerProfil(String repertoire) {
		File fichier =  new File(repertoire);
		Utilisateur utilisateur = null;
		
		//Vérifier que le fichier existe et qu'il peut être lu.
		if(fichier.exists() && fichier.canRead()) {
			try {
				ObjectInputStream flux =  new ObjectInputStream(new FileInputStream(fichier));
				utilisateur = (Utilisateur) flux.readObject();
				flux.close();
			} catch (IOException | ClassNotFoundException e) {
				fichier.delete();
				System.out.println("Échec du chargement du profil sauvegardé. La sauvegarde a été supprimé.");
			}
		} else {
			System.out.println("Aucune sauvegarde trouvé");
		}
		return utilisateur;
	}
	
	/**
	 * Sauvegarder le profil de l'utilisateur dans un fichier de sauvegarde.
	 * @param repertoire Le répertoire de sauvegarde du fichier.
	 * @param utilisateur L'utilisateur que l'on souhaite sauvegarder.
	 */
	private static void sauvegarderProfil(String repertoire, Utilisateur utilisateur) {
		File fichier =  new File(repertoire);
		try {
			fichier.createNewFile();
			if(fichier.exists() && fichier.canWrite()) {
				ObjectOutputStream flux =  new ObjectOutputStream(new FileOutputStream(fichier));
				flux.writeObject(utilisateur);
				flux.close();
			}
		} catch (IOException e) {
			System.out.println("Échec de la sauvegarde du profil.");
		}
	}
}
