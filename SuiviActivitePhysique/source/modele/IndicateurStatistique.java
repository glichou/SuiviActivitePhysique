package modele;

public class IndicateurStatistique <T extends Comparable<T>> {
	private int nbElement;
	private T min;
	private T max;
	private T total;
	private T moyenne;
	
	public IndicateurStatistique() {
		this.nbElement = 0;
	}
	
	public IndicateurStatistique(T min, T max, T total, T moyenne, int nbElement) {
		this.nbElement = nbElement;
		this.min = min;
		this.max = max;
		this.total = total;
		this.moyenne = moyenne;
	}
	
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
	
	public int getNbElement() {
		return nbElement;
	}

	public T getMin() {
		return min;
	}

	public T getMax() {
		return max;
	}

	public T getTotal() {
		return total;
	}

	public T getMoyenne() {
		return moyenne;
	}
}
