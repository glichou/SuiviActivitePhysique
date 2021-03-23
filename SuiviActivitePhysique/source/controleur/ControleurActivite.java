package controleur;

import java.util.ArrayList;

import modele.Activite;
import modele.ModeleApplication;
import vue.VueActivite;

public class ControleurActivite {
	private ModeleApplication modele;
	private VueActivite vue;
	
	/**
	 * Constructeur de la classe ControleurActivité.
	 * @param modele Le modèle de l'application.
	 * @param vue La vue de l'activité.
	 */
	public ControleurActivite(ModeleApplication modele, VueActivite vue) {
		this.modele = modele;
		this.vue = vue;
		this.vue.setControleur(this);
	}
	
	/**
	 * Récupérer l'ensemble des activités de l'utilisateur.
	 * @return Les activités de l'utilisateur.
	 */
	public ArrayList<Activite> recupererActivites(){
		return this.modele.getUtilisateur().getActivites();
	}
}
