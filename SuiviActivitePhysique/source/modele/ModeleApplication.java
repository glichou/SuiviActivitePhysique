package modele;

/**
 * Modèle de l'application.
 * @author Grégoire LICHOU
 * @author Quentin COUSTURIAN
 */
public class ModeleApplication {
	private Utilisateur utilisateur;
	
	/**
	 * Définir l'utilisateur de l'application
	 * @param prenom Le prénom de l'utilisateur.
	 * @param nom Le nom de l'utilisateur.
	 * @param taille La taille de l'utilisateur.
	 * @param poids Le poids de l'utilisateur.
	 */
	public void setUtilisateur(String prenom, String nom, int taille, double poids) {
		this.utilisateur = new Utilisateur(prenom, nom, taille, poids);
	}
	
	/**
	 * Définir l'utilisateur de l'application.
	 * @param utilisateur L'utilisateur de l'application.
	 */
	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}
	
	/**
	 * Récupérer l'utilisateur de l'application.
	 * @return L'utilisateur de l'application.
	 */
	public Utilisateur getUtilisateur() {
		return this.utilisateur;
	}
}
