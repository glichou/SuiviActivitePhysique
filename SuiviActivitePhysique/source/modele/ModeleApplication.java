package modele;

/**
 * Modèle de l'application.
 * @author lichou
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
	 * Récupérer l'utilisateur de l'application.
	 * @return L'utilisateur de l'application.
	 */
	public Utilisateur getUtilisateur() {
		return this.utilisateur;
	}
	
	/**
	 * Ajouter une activité dans le profil de l'utilisateur.
	 * @param activite L'activité à ajouter.
	 */
	public void ajouterActivite(Activite activite) {
		this.utilisateur.ajouterActivite(activite);
	}
	
	public void ajouterUnFavori(Favoris favori) {
		this.utilisateur.ajouterFavoris(favori);
	}
}
