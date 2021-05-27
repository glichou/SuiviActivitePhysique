package controleur;

import java.time.LocalDate;
import java.util.ArrayList;

import modele.Activite;
import modele.Statistique;
import modele.Utilisateur;
import vue.VueStatistiques;

public class ControleurStatistiques {
	private Utilisateur modele;
	private VueStatistiques vue;
	
	/**
	 * Constructeur de la classe du controleur des statistiques.
	 * @param modele Le modèle de l'application.
	 * @param vue La vue de l'application.
	 */
	public ControleurStatistiques(Utilisateur modele, VueStatistiques vue) {
		this.modele = modele;
		this.vue = vue;
		this.vue.setControleur(this);
	}
	
	/**
	 * Récupérer les statistiques des activités sur une période.
	 * @param debut Le début de la période.
	 * @param fin La fin de la période.
	 * @return Les statistiques des activités pour la période donné.
	 */
	public Statistique getStatistiques(LocalDate debut, LocalDate fin) {
		ArrayList<Activite> activites = this.modele.getActivites();
		return Statistique.genererStatistiques(debut, fin, activites);
	}
}
