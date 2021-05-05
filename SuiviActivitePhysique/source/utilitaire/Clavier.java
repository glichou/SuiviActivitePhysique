package utilitaire;

import java.io.InputStream;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Classe utilitaire permettant la mutualisation des
 * fonctions de contrôle de la saisie.
 * @author lichou
 * @version 0.1
 */
public class Clavier {
	private Scanner clavier;
	
	/**
	 * Constructeur de la classe Clavier
	 * @param entree Le flux d'entrée de la saisie.
	 */
	public Clavier(InputStream entree) {
		this.clavier = new Scanner(entree);
	}
	
	/**
	 * Récupérer un nombre saisit par l'utilisateur comprit entre
	 * une valeur minimum et maximum.
	 * @param min La valeur minimum attendu.
	 * @param max La valeur maximum attendu.
	 * @return Un nombre saisit par l'utilisateur.
	 */
	public int recupererNombre(int min, int max) {
		int numero = 0;
		boolean valide = false;
		
		while(!valide) {
			while(!clavier.hasNextInt()) {
				System.out.print("Veuillez saisir un nombre : ");
				clavier.next();
			} 
			
			numero = clavier.nextInt();
			if(numero >= min && numero <= max) {
				valide = true;
				clavier.nextLine();
			} else {
				System.out.print("Veuillez saisir un nombre compris entre " + min + " et " + max + " : ");
			}
		}
		return numero;
	}
	
	/**
	 * Récupérer un nombre décimal saisit par l'utilisateur comprit
	 * entre une valeur minimum et maximum.
	 * @param min La valeur minimum attendu.
	 * @param max La valeur maximum attendu.
	 * @return Un decimal saisit par l'utilisateur.
	 */
	public double recupererNombreDecimal(double min, double max) {
		double nombre = 0;
		boolean valide = false;
		
		while(!valide) {
			while(!clavier.hasNextDouble()) {
				System.out.print("Veuillez saisir un nombre décimal : ");
				clavier.next();
			}
			
			nombre = clavier.nextDouble();
			
			if(nombre >= min && nombre <= max) {
				valide = true;
				clavier.nextLine();
			} else {
				System.out.print("Veuillez saisir un nombre décimal compris entre " + min + " et " + max + " : ");
			}
		}
		return nombre;
	}
	
	/**
	 * Récupérer un nombre long saisit par l'utilisateur comprit
	 * entre une valeur minimum et maximum.
	 * @param min La valeur minimum attendu.
	 * @param max La valeur maximum attendu.
	 * @return Un nombre saisit par l'utilisateur.
	 */
	public long recupererGrandNombre(long min, long max) {
		long nombre = 0;
		boolean valide = false;
		
		while(!valide) {
			while(!clavier.hasNextLong()) {
				System.out.print("Veuillez saisir un nombre entier : ");
				clavier.next();
			}

			nombre = clavier.nextLong();			
			if(nombre >= min && nombre <= max) {
				valide = true;
				clavier.nextLine();
			} else {
				System.out.print("Veuillez saisir un nombre entier compris entre " + min + " et " + max + " : ");
			}
		}
		return nombre;
	}
	
	
	
	/**
	 * Récupérer une durée saisit par l'utilisateur comprit entre
	 * une valeur minimum et maximum exprimé en minutes.
	 * @param min La durée minimum en minutes.
	 * @param max La durée maximum en minutes.
	 * @return Une durée correspondant à la valeur saisit par l'utilisateur.
	 */
	public Duration recupererDuree(long min, long max) {
		Duration duree = null;
		boolean valide = false;
		long nombre;
		
		while(!valide) {			
			while(!clavier.hasNextLong()) {
				System.out.print("Veuillez saisir un nombre : ");
				clavier.next();
			} 
			
			nombre = clavier.nextLong();
			if(nombre >= min && nombre <= max) {
				duree = Duration.ofMinutes(nombre);
				valide = true;
				clavier.nextLine();
			} else {
				System.out.print("Veuillez saisir un nombre compris entre " + min + " et " + max + " : ");
			}
		}
		return duree;
	}
	
	/**
	 * Récupérer une heure saisit par l'utilisateur.
	 * @param autoriserHeureParDefaut Autoriser l'enregistrement de l'heure actuelle si 
	 * l'utilisateur ne saisit rien.
	 * @return L'heure saisit par l'utilisateur ou celle actuelle.
	 */
	public LocalTime recupererHeure(boolean autoriserHeureParDefaut) {
		DateTimeFormatter format = DateTimeFormatter.ofPattern("H'h'mm");
		LocalTime heure = LocalTime.now();
		
		boolean valide = false;
		String chaine;
		do {
			chaine = clavier.nextLine().trim();
			
			if(chaine.length() > 0) {
				try {
					heure = LocalTime.parse(chaine, format);
					valide = true;
				} catch(DateTimeParseException exception) {
					System.out.print("Veuillez saisir une date valide au format " + heure.format(format) +  " : ");
				}
			} else if(autoriserHeureParDefaut) {
				System.out.println("Vous n'avez rien saisi. La valeur par défaut [" + heure.format(format) + "] a été enregistré.");
				valide = true;
			}
		} while(!valide);
		
		return heure;
	}
	
	/**
	 * Récupérer une date valide saisie par l'utilisateur.
	 * @param autoriserDateParDefaut Autoriser l'enregistrement de la date actuelle si 
	 * l'utilisateur ne saisit rien.
	 * @return La date saisie par l'utilisateur.
	 */
	public LocalDate recupererDate(boolean autoriserDateParDefaut) {
		DateTimeFormatter format = DateTimeFormatter.ofPattern("dd'/'MM'/'yyyy");
		LocalDate date = LocalDate.now();
		boolean valide = false;
		String chaine;

		do {
			chaine = clavier.nextLine().trim();
			
			if(chaine.length() > 0) {
				try {
					date = LocalDate.parse(chaine, format);
					valide = true;
				} catch(DateTimeParseException exception) {
					System.out.print("Veuillez saisir une date valide au format " + date.format(format) +  " : ");
				}
			} else {
				System.out.println("Vous n'avez rien saisi. La valeur par défaut [" + date.format(format) + "] a été enregistré.");
				valide = true;
			}
		} while(!valide);
		
		return date;
	}
	
	/**
	 * Récupérer une valide URL saisit par l'utilisateur.
	 * @return L'URL valide saisit par l'utilisateur.
	 */
	public String recupererURL() {
		String chaine = null;
		boolean valide = false;
		
		Pattern modele = Pattern.compile("^(https?://)?[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]");
		
		
		while(!valide) {
			chaine = clavier.nextLine().trim();
			
			if(chaine.length() > 0) {
				Matcher expression = modele.matcher(chaine); 
				
				if(expression.matches()) {
					valide = true;
				} else {
					System.out.print("Veuillez saisir une URL valide : ");
				}
			} else {
				System.out.print("Vous n'avez rien saisi. Veuillez saisir une URL : ");
			}
		}
		return chaine;
	}
	
	/**
	 * Récupérer un texte long saisit par l'utilisateur.
	 * @return Le texte saisit par l'utilisateur.
	 */
	public String recupererTexteLong() {
		String chaineFinale = "";
		String chaine = null;
		int nbSautLigne = 0;
		
		System.out.println("ASTUCE: Saissez deux retour à la ligne pour terminer.");
		while(nbSautLigne < 2) {
			System.out.print(">> ");
			chaine = clavier.nextLine();
			
			nbSautLigne++;
			
			if(chaine.length() > 0) {
				//Ajouter les saut de lignes à la chaine finale.
				for(int i = 0; i < nbSautLigne; i++) {
					chaineFinale += "\n";
				}
				chaineFinale +=  chaine;
				nbSautLigne = 0;
			}
		}
		return chaineFinale;
	}
	
	/**
	 * Récupérer un texte saisit par l'utilisateur.
	 * @param chiffreAutorise Autoriser la saisit de chiffre dans le texte.
	 * @param espaceAutorise Autoriser la saisit d'espace dans le texte.
	 * @return Le texte court saisit par l'utilisateur.
	 */
	public String recupererTexteCourt(boolean chiffreAutorise, boolean espaceAutorise) {
		String chaine = null;
		boolean valide = false;
		
		do {
			chaine = clavier.nextLine().trim();
			
			if(chaine.length() > 0) {
				if(chiffreAutorise && espaceAutorise) {
					valide = true;
				} else {
					if(!chiffreAutorise && chaine.matches("\\p{N}")) {
						System.out.print("Vous avez saisi un chiffre. Veuillez saisir un texte sans chiffre : ");
					} else if(!espaceAutorise && chaine.matches("\\p{Zs}")) {
						System.out.print("Vous avez saisi un espace. Veuillez saisir un texte sans espace : ");
					} else {
						valide = true;
					}
				}
			} else {
				System.out.print("Vous n'avez rien saisi. Veuillez saisir un texte court : ");
			}
		}while(!valide);
		return chaine;
	}
	
	public static void modifierCouleurPolice() {
		System.out.println((char)27 + "[31m" + "ERROR MESSAGE IN RED");

		System.out.print("\033[0;35m");
	}
	
	/**
	 * Effacer l'écran (possible sur certains système d'exploitation)
	 */
	public static void effacerEcran () {
        String systemeExploitation = System.getProperty("os.name");
        
        if (systemeExploitation.contains("Windows")) {
            try {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } catch (Exception exception) {
            }
        } else if (systemeExploitation.contains("Mac")) {
            System.out.print("\033[H\033[2J");
        } else {
            System.out.println("Unable to determine system info. Unable to clear screen.");
        }
	 }
}