package modele;

/**
 * Interface permettant de redéfinir les méthodes de calcul des
 * total et moyenne selon des objets.
 * @author Grégoire LICHOU
 *
 * @param <T> Le type de l'objet.
 */
public interface CalculateurStatistique <T> {
	
	/**
	 * Méthode utilisé pour déterminer le calcul du total.
	 * @param total La valeur du total précédente.
	 * @param element La valeur à ajouter au total.
	 * @return Le nouveau total.
	 */
	public abstract T calculerTotal(T total, T element);
	
	
	/**
	 * Méthode utilisé pour déterminer le calcul de la moyenne.
	 * @param total Le total des valeurs trouvés.
	 * @param nbElement Le nombre d'éléments.
	 * @return La nouvelle moyenne.
	 */
	public abstract T calculerMoyenne(T total, int nbElement);
}
