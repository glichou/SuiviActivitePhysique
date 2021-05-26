package vue;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Optional;

import controleur.ControleurFavori;
import modele.Categorie;
import modele.Favori;
import utilitaire.Clavier;

/**
* Classe de la vue favoris.
 * @author Grégoire LICHOU
 * @author Quentin COUSTURIAN
*/
public class VueFavori {
	private ControleurFavori controleur;
	private Clavier clavier;
	
	/**
	* Constructeur de la classe de VueFavoris.
	*/
	public VueFavori() {
		clavier = new Clavier(System.in);
	}
	
	/**
	* Définir le contrôleur de cette vue.
	* @param controleur Le controleur de la vue.
	*/
	public void setControleur(ControleurFavori controleur) {
		this.controleur = controleur;
	}
	
	/**
	* Afficher le menu principal de sélection des options pour les favoris.
	*/
	public void afficherMenu() {
		int numeroSaisi;
		do {
			System.out.println("\t[FAVORIS]\n");
			System.out.println("1] Afficher les favoris");
			System.out.println("2] Afficher un favori");
			System.out.println("3] Ajouter un favori");
			System.out.println("4] Modifier un favori");
			System.out.println("5] Supprimer un favori");
			System.out.println("6] Revenir à l'accueil");
			System.out.print("\nSaisir l'option voulue: ");
			
			numeroSaisi = clavier.recupererNombre(1, 6);
			System.out.println();
	
			//Executer l'action demandé par l'utilisateur.
			switch(numeroSaisi) {
				case 1:
					this.afficherLesFavoris();
					break;
				case 2:
					this.afficherUnFavori();
					break;
				case 3:
					this.ajouterUnFavori();
					break;
				case 4:
					this.modifierUnFavori();
					break;
				case 5:
					this.supprimerUnFavori();
					break;
			}
		} while(numeroSaisi != 6);
	}
	
	/**
	 * Afficher le menu permettant de supprimer un favoris
	 */
	public void supprimerUnFavori() {		
		int index = 0;
		if(this.controleur.recupererNbFavoris() > 0) {
			
			System.out.print("Veuillez saisir l'identifiant du favori : ");
			index = clavier.recupererNombre(1, this.controleur.recupererNbFavoris());
			
			try {
				this.controleur.supprimerFavoris(index - 1);
				System.out.println("Le favoris selectionné a bien été supprimé !");
			} catch (Exception e) {
				System.out.println("Le favoris selectionné n'existe pas !");
			}
			
		} else {
			System.out.println("→ Vous n'avez encore aucun favori pour le moment !");
		}
		System.out.println();
	}
	
	public void afficherUnFavori() {
		int index = 0;
		if(this.controleur.recupererNbFavoris() > 0) {
			System.out.print("Veuillez saisir l'identifiant du favori : ");
			
			//Demander l'identifiant des favoris si la liste n'est pas vide.
			index = clavier.recupererNombre(1, this.controleur.recupererNbFavoris());
			try {
				System.out.println(this.controleur.recupererFavoris(index - 1));
			} catch (Exception e) {
				System.out.println("Le favoris selectionné n'existe pas !");
			}
		} else {
			System.out.println("→ Vous n'avez encore aucun favori pour le moment !");
		}
		System.out.println();
	}
	
	/**
	 * Afficher l'ensemble des activités de l'utilisateurs.
	 */	
	public void afficherLesFavoris() {
		//Récupérer les favoris dans le profil de l'utilisateur.
		ArrayList<Favori> liste = this.controleur.recupererFavoris();
		
		//Lister tous les favoris de l'utilisateur avec un identifiant s'il y en a.
		if(liste.size() > 0) {
			System.out.println("Voici la liste de vos favoris :");
			for(int i = 0; i < liste.size(); i++) {
				System.out.println((i + 1) + "] " + liste.get(i).toSmallString());
			}
		} else {
			//Afficher un message car il n'y a pas de favoris dans la liste.
			System.out.println("→ Vous n'avez encore aucun favoris pour le moment !");
		}
		System.out.println();
	}
	
	// Récupérer les informations pour ajouter un favori dans le profil de l'utilisateur.
	public void ajouterUnFavori() {
		System.out.println(" [AJOUT D'UN FAVORI]\n");
		
		System.out.print("Titre: ");
		String titre = clavier.recupererTexteCourt(true, true);
		
		System.out.print("URL: ");
		String lien = clavier.recupererURL();
		
		System.out.print("Durée (en minutes): ");
		Duration duree = clavier.recupererDuree(0, 2880);
		
		Categorie categorie = this.controleur.afficherSelectionCategorie();
		
		System.out.println("Mémo: ");
		String memo = clavier.recupererTexteLong();
		
		// Ajoute le favori
		this.controleur.ajouterUnFavori(titre, lien, duree, categorie, memo);
	}
	
	public void modifierUnFavori() {
		int index = 0;
		if(this.controleur.recupererNbFavoris() > 0) {
			
			System.out.print("Veuillez saisir l'identifiant du favori : ");
			index = clavier.recupererNombre(1, this.controleur.recupererNbFavoris());
			
			try {
				Favori favori = this.controleur.recupererFavoris(index - 1);
				
				System.out.print("Titre: ");
				String titre = clavier.recupererTexteCourt(true, true, Optional.of(favori.getTitre()));
				favori.setTitre(titre);
				
				System.out.print("URL: ");
				String lien = clavier.recupererURL(Optional.of(favori.getLien()));
				favori.setLien(lien);
				
				System.out.print("Durée (en minutes): ");
				Duration duree = clavier.recupererDuree(0, 2880, Optional.of(favori.getDuree()));
				favori.setDuree(duree);
				
				Categorie categorie = this.controleur.afficherSelectionCategorie(favori.getCategorie());
				favori.setCategorie(categorie);
				
				System.out.println("Mémo: ");
				String memo = clavier.recupererTexteLong(Optional.of(favori.getMemo()));
				favori.setMemo(memo);
				
			} catch (Exception e) {
				System.out.println("Le favoris selectionné n'existe pas !");
			}
			
		} else {
			System.out.println("→ Vous n'avez encore aucun favori pour le moment !");
		}
	}
}