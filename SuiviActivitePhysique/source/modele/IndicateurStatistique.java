package modele;

/**
 * Classe permettant de représenter un ensemble de statistiques basiques.
 * @author Grégoire LICHOU
 * @author Quentin COUSTURIAN
 *
 * @param <T> Le type des statistiques à représenter.
 */
public class IndicateurStatistique <T extends Comparable<T>> {
	private int nbElement;
	private T min;
	private T max;
	private T total;
	private T moyenne;
	
	/**
	 * Constructeur d'un indicateur de statistique vide.
	 */
	public IndicateurStatistique() {
		this.nbElement = 0;
	}
	
	/**
	 * Constructeur d'un indicateur de statistique.
	 * @param min La plus petite valeur.
	 * @param max La plus grande valeur.
	 * @param total La valeur totales.
	 * @param moyenne La moyenne des valeurs.
	 * @param nbElement Le nombre d'élements ayant servis à déterminer cet indicateur.
	 * Cette valeur est utilisé dans le cas où un autre élément est ajouté. 
	 */
	public IndicateurStatistique(T min, T max, T total, T moyenne, int nbElement) {
		this.nbElement = nbElement;
		this.min = min;
		this.max = max;
		this.total = total;
		this.moyenne = moyenne;
	}
	
	/**
	 * Ajouter un élément à prendre en compte dans le calcul des statistiques.
	 * @param element L'élement à ajouter.
	 * @param calculateur L'interface à utiliser pour le calcul.
	 */
	public void ajouterElement(T element, CalculateurStatistique<T> calculateur) {
		nbElement++;
		if(min == null || element.compareTo(min) < 0) {
			min = element;
		}
		if(max == null || element.compareTo(max) > 0) {
			max = element;
		}
		if(total == null) {
			total = element;
		} else {
			total = calculateur.calculerTotal(total, element);
		}
		if(moyenne == null) {
			moyenne = element;
		} else {
			moyenne = calculateur.calculerMoyenne(total, nbElement);
		}
	}
	
	/**
	 * Récupérer la valeur minimum.
	 * @return La valeur minimum.
	 */
	public T getMin() {
		return min;
	}
	
	/**
	 * Récupérer la valeur maximum.
	 * @return La valeur maximum.
	 */
	public T getMax() {
		return max;
	}

	/**
	 * Récupérer le total des valeurs.
	 * @return Le total des valeurs.
	 */
	public T getTotal() {
		return total;
	}

	/**
	 * Récupérer la moyenne des valeurs.
	 * @return La moyenne des valeurs.
	 */
	public T getMoyenne() {
		return moyenne;
	}
}
