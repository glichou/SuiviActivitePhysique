package modele;

public interface CalculateurStatistique <T> {
	
	public abstract T calculerTotal(T total, T element);
	
	public abstract T calculerMoyenne(T total, int nbElement);
}
