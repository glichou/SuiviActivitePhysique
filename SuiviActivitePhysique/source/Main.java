import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import controleur.ControleurPrincipal;
import modele.Activite;
import modele.Categorie;
import modele.Difficulte;
import modele.ModeleApplication;
import modele.Statistique;
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
		/**ArrayList<Activite> maListe = new ArrayList<>();
		maListe.add(new Activite(LocalDateTime.of(2021, 01, 01, 10, 25), Difficulte.NEUF, Duration.ofMinutes(12), 0, new Categorie("test")));
		maListe.add(new Activite(LocalDateTime.of(2021, 01, 01, 10, 25), Difficulte.NEUF, Duration.ofMinutes(45), 100, new Categorie("test")));
		maListe.add(new Activite(LocalDateTime.of(2021, 01, 01, 10, 25), Difficulte.NEUF, Duration.ofMinutes(45), 10, new Categorie("test")));
		maListe.add(new Activite(LocalDateTime.of(2021, 01, 01, 10, 25), Difficulte.NEUF, Duration.ofMinutes(35), 10, new Categorie("test")));
		maListe.add(new Activite(LocalDateTime.of(2021, 01, 01, 10, 25), Difficulte.NEUF, Duration.ofMinutes(45), 10, new Categorie("test")));
		maListe.add(new Activite(LocalDateTime.of(2021, 01, 01, 10, 25), Difficulte.NEUF, Duration.ofMinutes(120), 10, new Categorie("test")));
		maListe.add(new Activite(LocalDateTime.of(2021, 01, 01, 10, 25), Difficulte.NEUF, Duration.ofMinutes(45), 10, new Categorie("test")));
		maListe.add(new Activite(LocalDateTime.of(2021, 01, 01, 10, 25), Difficulte.NEUF, Duration.ofMinutes(45), 10, new Categorie("test")));
		maListe.add(new Activite(LocalDateTime.of(2021, 01, 01, 10, 25), Difficulte.NEUF, Duration.ofMinutes(360), 10, new Categorie("test")));
		maListe.add(new Activite(LocalDateTime.of(2021, 01, 01, 10, 25), Difficulte.NEUF, Duration.ofMinutes(45), 10, new Categorie("test")));
		maListe.add(new Activite(LocalDateTime.of(2012, 01, 01, 10, 25), Difficulte.NEUF, Duration.ofMinutes(2450), 10, new Categorie("test")));
		System.out.println("Avant:" + maListe.size());
		Statistique stat = Statistique.genererStatistiques(LocalDate.of(2021, 01, 01), LocalDate.of(2022, 01, 01), maListe);
		System.out.println("Après" + maListe.size());
		
		System.out.println("Max distance : " + stat.getDistanceParcouru().getMax() + " km");
		System.out.println("Min distance : " + stat.getDistanceParcouru().getMin() + " km");
		System.out.println("Moy distance : " + stat.getDistanceParcouru().getMoyenne() + " km");
		System.out.println("Tot distance : " + stat.getDistanceParcouru().getTotal() + " km");
		System.out.println("Min duree : " + stat.getDuree().getMin());
		System.out.println("Max duree : " + stat.getDuree().getMax());
		System.out.println("Moy duree : " + stat.getDuree().getMoyenne());
		System.out.println("Tot duree : " + stat.getDuree().getTotal());
		**/
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
