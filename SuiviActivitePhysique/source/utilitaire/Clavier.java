package utilitaire;

import java.io.InputStream;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Optional;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import modele.Difficulte;

/**
 * Classe utilitaire permettant la mutualisation des
 * fonctions de contrôle de la saisie.
 * @author Grégoire LICHOU
 * @author Quentin COUSTURIAN
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
		return recupererNombre(min, max, Optional.empty());
	}
	
	/**
	 * Récupérer un nombre saisit par l'utilisateur comprit entre
	 * une valeur minimum et maximum.
	 * @param min La valeur minimum attendu.
	 * @param max La valeur maximum attendu.
	 * @param valeurParDefaut La valeur à utiliser par défaut.
	 * @return Un nombre saisit par l'utilisateur.
	 */
	public int recupererNombre(int min, int max, Optional<Integer> valeurParDefaut) {
		int numero = 0;
		boolean valide = false;
		String chaine = null;
		
		while(!valide) {
			chaine = clavier.nextLine();
			
			if(!chaine.isEmpty()) {
				try {
					numero = Integer.parseInt(chaine);
					
					if(numero >= min && numero <= max) {
						valide = true;
					} else {
						System.out.print("Veuillez saisir un nombre compris entre " + min + " et " + max + " : ");
					}
			    } catch (NumberFormatException exception) {
			    	System.out.print("Veuillez saisir un nombre : ");
			    }	
			} else {
				if(valeurParDefaut.isPresent()) {
					numero = valeurParDefaut.get().intValue();
					valide = true;
					System.out.println("Vous n'avez rien saisi. La valeur par défaut [" + numero + "] a été enregistré.");
				} else {
					System.out.print("Vous n'avez rien saisi ! Veuillez saisir un nombre : ");
				}
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
		return recupererNombreDecimal(min, max, Optional.empty());
	}
	
	/**
	 * Récupérer un nombre décimal saisit par l'utilisateur comprit
	 * entre une valeur minimum et maximum.
	 * @param min La valeur minimum attendu.
	 * @param max La valeur maximum attendu.
	 * @param valeurParDefaut La valeur à utiliser par défaut.
	 * @return Un decimal saisit par l'utilisateur.
	 */
	public double recupererNombreDecimal(double min, double max, Optional<Double> valeurParDefaut) {
		double nombre = 0;
		boolean valide = false;
		String chaine = null;
		
		while(!valide) {
			chaine = clavier.nextLine();	
				
			if(!chaine.isEmpty()) {
				try {
					nombre = Double.parseDouble(chaine);
					
					if(nombre >= min && nombre <= max) {
						valide = true;
					} else {
						System.out.print("Veuillez saisir un nombre décimal compris entre " + min + " et " + max + " : ");
					}
			    } catch (NumberFormatException exception) {
			    	System.out.print("Veuillez saisir un nombre décimal : ");
			    }
			} else {
				if(valeurParDefaut.isPresent()) {
					nombre = valeurParDefaut.get().doubleValue();
					valide = true;
					System.out.println("Vous n'avez rien saisi. La valeur par défaut [" + nombre + "] a été enregistré.");
				} else {
					System.out.print("Vous n'avez rien saisi. Veuillez saisir un nombre décimal : ");
				}
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
		return recupererGrandNombre(min, max, Optional.empty());
	}
	
	/**
	 * Récupérer un nombre long saisit par l'utilisateur comprit
	 * entre une valeur minimum et maximum.
	 * @param min La valeur minimum attendu.
	 * @param max La valeur maximum attendu.
	 * @param valeurParDefaut La valeur à utiliser par défaut.
	 * @return Un nombre saisit par l'utilisateur.
	 */
	public long recupererGrandNombre(long min, long max, Optional<Long> valeurParDefaut) {
		long nombre = 0;
		boolean valide = false;
		String chaine = null;
		
		while(!valide) {		
			chaine = clavier.nextLine();
				
			if(!chaine.isEmpty()) {
				try {
					nombre = Long.parseLong(chaine);
					
					if(nombre >= min && nombre <= max) {
						valide = true;
					} else {
						System.out.print("Veuillez saisir un nombre entier compris entre " + min + " et " + max + " : ");
					}
				 } catch (NumberFormatException exception) {
					 System.out.print("Veuillez saisir un nombre entier : ");
				 }
			} else {
				if(valeurParDefaut.isPresent()) {
					nombre = valeurParDefaut.get().longValue();
					valide = true;
					System.out.println("Vous n'avez rien saisi. La valeur par défaut [" + nombre + "] a été enregistré.");
				} else {
					System.out.print("Vous n'avez rien saisi. Veuillez saisir un nombre décimal : ");
				}
			}
		}
		return nombre;
	}
	
	/**
	 * Récupérer une difficulté saisit par l'utilisateur comprit
	 * entre une valeur minimum et maximum.
	 * @return Un nombre saisit par l'utilisateur.
	 */
	public Difficulte recupererDifficulte() {
		return recupererDifficulte(Optional.empty());
	}
	
	/**
	 * Récupérer une difficulté saisit par l'utilisateur comprit
	 * entre une valeur minimum et maximum.
	 * @param valeurParDefaut La valeur à utiliser par défaut.
	 * @return Un nombre saisit par l'utilisateur.
	 */
	public Difficulte recupererDifficulte(Optional<Difficulte> valeurParDefaut) {
		short nombre = 0;
		boolean valide = false;
		String chaine = null;
		Difficulte difficulte = null;
		int difficilteMax = Difficulte.values().length;
		
		while(!valide) {
			chaine = clavier.nextLine();
			
			if(!chaine.isEmpty()) {
				try {
					nombre = Short.parseShort(chaine);
					
					if(nombre >= 1 && nombre <= difficilteMax) {
						difficulte = Difficulte.values()[nombre - 1];
						valide = true;
					} else {
						System.out.print("Veuillez saisir un nombre entier compris entre 1 et " + difficilteMax + " : ");
					}
			    } catch (NumberFormatException exception) {
			    	System.out.print("Veuillez saisir un nombre entier : ");
			    }
			} else {
				if(valeurParDefaut.isPresent()) {
					difficulte = valeurParDefaut.get();
					valide = true;
					System.out.println("Vous n'avez rien saisi. La valeur par défaut [" + difficulte.toString() + "] a été enregistré.");
				} else {
					System.out.print("Vous n'avez rien saisi. Veuillez saisir un nombre entier : ");
				}
			}
			
		}
		return difficulte;
	}

	/**
	 * Récupérer une durée saisit par l'utilisateur comprit entre
	 * une valeur minimum et maximum exprimé en minutes.
	 * @param min La durée minimum en minutes.
	 * @param max La durée maximum en minutes.
	 * @param valeurParDefaut La valeur par défaut si rien n'est saisit par l'utilisateur.
	 * @return Une durée correspondant à la valeur saisit par l'utilisateur.
	 */
	public Duration recupererDuree(long min, long max, Optional<Duration> valeurParDefaut) {
		Duration duree = null;
		boolean valide = false;
		String chaine = null;
		long nombre;
		
		while(!valide) {
			chaine = clavier.nextLine();
			
			if(!chaine.isEmpty()) {
				try {
					nombre = Long.parseLong(chaine);
					
					if(nombre >= min && nombre <= max) {
						duree = Duration.ofMinutes(nombre);
						valide = true;
					} else {
						System.out.print("Veuillez saisir un nombre compris entre " + min + " et " + max + " : ");
					}
			    } catch (NumberFormatException exception) {
			    	System.out.print("Veuillez saisir un nombre entier : ");
			    }
			} else {
				if(valeurParDefaut.isPresent()) {
					duree = valeurParDefaut.get();
					valide = true;
					System.out.println("Vous n'avez rien saisi. La valeur par défaut [" + duree.toMinutes() + " minutes] a été enregistré.");
				} else {
					System.out.print("Vous n'avez rien saisi. Veuillez saisir un nombre entier : ");
				}
			}
		}
		return duree;
	}
	
	/**
	 * Récupérer une durée saisit par l'utilisateur comprit entre
	 * une valeur minimum et maximum exprimé en minutes.
	 * @param min La durée minimum en minutes.
	 * @param max La durée maximum en minutes.
	 * @return Une durée correspondant à la valeur saisit par l'utilisateur.
	 */
	public Duration recupererDuree(long min, long max) {
		return recupererDuree(min, max, Optional.empty());
	}
	
	/**
	 * Récupérer une heure saisit par l'utilisateur.
	 * @return L'heure saisit par l'utilisateur ou celle actuelle.
	 */
	public LocalTime recupererHeure() {
		return recupererHeure(Optional.empty());
	}
	
	/**
	 * Récupérer une heure saisit par l'utilisateur.
	 * @param valeurParDefaut La valeur à utiliser par défaut.
	 * @return L'heure saisit par l'utilisateur ou celle actuelle.
	 */
	public LocalTime recupererHeure(Optional<LocalTime> valeurParDefaut) {
		DateTimeFormatter format = DateTimeFormatter.ofPattern("H'h'mm");
		LocalTime heure = LocalTime.now();
		
		boolean valide = false;
		String chaine;
		do {
			chaine = clavier.nextLine().trim();
			
			if(!chaine.isEmpty()) {
				try {
					heure = LocalTime.parse(chaine, format);
					valide = true;
				} catch(DateTimeParseException exception) {
					System.out.print("Veuillez saisir une heure valide au format " + heure.format(format) +  " : ");
				}
			} else {
				if(valeurParDefaut.isPresent()) {
					heure = valeurParDefaut.get();
					valide = true;
					System.out.println("Vous n'avez rien saisi. La valeur par défaut [" + heure.format(format) + "] a été enregistré.");
				} else {
					System.out.print("Vous n'avez rien saisi. Veuillez saisir une heure  : ");
				}
			}
		} while(!valide);
		
		return heure;
	}
	
	/**
	 * Récupérer une date valide saisie par l'utilisateur.
	 * @param valeurParDefaut La valeur à utiliser par défaut.
	 * @return La date saisie par l'utilisateur.
	 */
	public LocalDate recupererDate(Optional<LocalDate> valeurParDefaut) {
		DateTimeFormatter format = DateTimeFormatter.ofPattern("dd'/'MM'/'yyyy");
		LocalDate date = LocalDate.now();
		boolean valide = false;
		String chaine;

		do {
			chaine = clavier.nextLine().trim();
			
			if(!chaine.isEmpty()) {
				try {
					date = LocalDate.parse(chaine, format);
					valide = true;
				} catch(DateTimeParseException exception) {
					System.out.print("Veuillez saisir une date valide au format " + date.format(format) +  " : ");
				}
			} else {
				if(valeurParDefaut.isPresent()) {
					date = valeurParDefaut.get();
					valide = true;
					System.out.println("Vous n'avez rien saisi. La valeur par défaut [" + date.format(format) + "] a été enregistré.");
				} else {
					System.out.print("Vous n'avez rien saisi. Veuillez saisir une date  : ");
				}
			}
		} while(!valide);
		
		return date;
	}
	
	/**
	 * Récupérer une date valide saisie par l'utilisateur.
	 * @return La date saisie par l'utilisateur.
	 */
	public LocalDate recupererDate() {
		return recupererDate(Optional.empty());
	}
	
	/**
	 * Récupérer une valide URL saisit par l'utilisateur.
	 * @return L'URL valide saisit par l'utilisateur.
	 */
	public String recupererURL() {
		return recupererURL(Optional.empty());
	}
	
	/**
	 * Récupérer une valide URL saisit par l'utilisateur.
	 * @param valeurParDefaut La valeur à utiliser par défaut.
	 * @return L'URL valide saisit par l'utilisateur.
	 */
	public String recupererURL(Optional<String> valeurParDefaut) {
		String chaine = null;
		boolean valide = false;
		
		Pattern modele = Pattern.compile("(?:(?<protocole>https?)://)?(www\\.)?(?<domaine>([a-z0-9](?:[a-z0-9-]{0,61}[a-z0-9])?\\.)+[a-z][a-z0-9-]{0,61}[a-z0-9])(?:(?:(?:(?:/[a-zA-Z_0-9\\.-]+)+/?)|/)(?:\\?[^#]*)?(?:#[a-zA-Z][a-zA-Z0-9-]*[a-zA-Z0-9])?)?");
		
		while(!valide) {
			chaine = clavier.nextLine().trim();
			
			if(!chaine.isEmpty()) {
				Matcher expression = modele.matcher(chaine); 
				
				if(expression.matches()) {
					valide = true;
				} else {
					System.out.print("Veuillez saisir une URL valide : ");
				}
			} else {
				if(valeurParDefaut.isPresent()) {
					chaine = valeurParDefaut.get();
					valide = true;
					System.out.println("Vous n'avez rien saisi. La valeur par défaut [" + chaine + "] a été enregistré.");
				} else {
					System.out.print("Vous n'avez rien saisi. Veuillez saisir une URL : ");
				}
			}
		}
		return chaine;
	}
	
	/**
	 * Récupérer un texte long saisit par l'utilisateur.
	 * @return Le texte saisit par l'utilisateur.
	 */
	public String recupererTexteLong() {
		return recupererTexteLong(Optional.empty());
	}
	
	/**
	 * Récupérer un texte long saisit par l'utilisateur avec
	 * une valeur par défaut utilisé en cas de retour à la ligne.
	 * @param valeurParDefaut La valeur à utiliser par défaut.
	 * @return Le texte saisit par l'utilisateur ou la valeur par défaut.
	 */
	public String recupererTexteLong(Optional<String> valeurParDefaut) {
		String chaineFinale = "";
		String chaine = null;
		int nbSautLigne = 0;
		
		System.out.println("ASTUCE: Saissez deux retours à la ligne pour terminer.");
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
		if(valeurParDefaut.isPresent() && chaineFinale.isEmpty()) {
			chaineFinale = valeurParDefaut.get();
			System.out.println("Vous n'avez rien saisi. La valeur par défaut a été enregistré.");
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
		return recupererTexteCourt(chiffreAutorise, espaceAutorise, Optional.empty());
	}
	
	/**
	 * Récupérer un texte saisit par l'utilisateur.
	 * @param chiffreAutorise Autoriser la saisit de chiffre dans le texte.
	 * @param espaceAutorise Autoriser la saisit d'espace dans le texte.
	 * @param valeurParDefaut La valeur à utiliser par défaut.
	 * @return Le texte court saisit par l'utilisateur.
	 */
	public String recupererTexteCourt(boolean chiffreAutorise, boolean espaceAutorise, Optional<String> valeurParDefaut) {
		String chaine = null;
		boolean valide = false;
		
		do {
			chaine = clavier.nextLine().trim();
			
			if(!chaine.isEmpty()) {
				if(chiffreAutorise && espaceAutorise) {
					valide = true;
				} else {
					if(!chiffreAutorise && chaine.matches("(.*)\\p{N}(.*)")) {
						System.out.print("Vous avez saisi un chiffre. Veuillez saisir un texte sans chiffre : ");
					} else if(!espaceAutorise && chaine.matches("(.*)\\p{Zs}(.*)")) {
						System.out.print("Vous avez saisi un espace. Veuillez saisir un texte sans espace : ");
					} else {
						valide = true;
					}
				}
			} else {
				if(valeurParDefaut.isPresent()) {
					chaine = valeurParDefaut.get();
					valide = true;
					System.out.println("Vous n'avez rien saisi. La valeur par défaut [" + chaine + "] a été enregistré.");
				} else {
					System.out.print("Vous n'avez rien saisi. Veuillez saisir un texte court : ");
				}
			}
		}while(!valide);
		return chaine;
	}
}
