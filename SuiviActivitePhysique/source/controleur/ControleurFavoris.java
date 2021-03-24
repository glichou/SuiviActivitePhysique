package controleur;

import java.util.ArrayList;

import modele.ModeleApplication;
import modele.Favoris;
import vue.vueFavoris;

public class ControleurFavoris {
	private ModeleApplication modele;
	private VueFavoris vue;
	
	
	
	
	public ControleurFavoris(ModeleApplication modele, VueFavoris vue) {
		this.modele = modele;
		this.vue = vue;
		this.vue.setControleur(this);
	}
	
	
	public ArrayList<Favoris> recupererFavoris(){
		return this.modele.getUtilisateur().getFavoris();
	}

}
