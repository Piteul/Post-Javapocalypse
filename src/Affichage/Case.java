package Affichage;

import Objet.Arme;
import Objet.Objet;
import Objet.ObjetDivers;
import Objet.ObjetVital;
import Outils.Outils;

/**
 * Classe définissant chaque case composant la grille de jeu
 * 
 * @author Axel Tétart
 *
 */
public class Case {

	Outils outils = new Outils();
	private char symbole;
	private String description;
	private Objet objet;

	// objet trouvable en zone hostile (*)
	Objet objetZoneHostile[] = { new ObjetVital("Haricot en boite", 1), new ObjetVital("Eau usée", 1),
			new ObjetVital("Eau propre", 1), new ObjetVital("Soda", 1), new ObjetVital("Viande cru", 1),
			new ObjetVital("Sirop pour la toux", 1), new ObjetVital("Bandage", 1), new ObjetVital("Morphine", 1),
			new ObjetVital("Vitamine C", 1), new Arme("Pistolet"), new Arme("Fusil à pompe"), new Arme("Couteau"),
			new Arme("Marteau"), new Arme("Machette"), new ObjetDivers("Combustible", 1),
			new ObjetDivers("Allumette", 1) };

	// objet trouvable en zone securisé (#)
	Objet objetZoneSecurise[] = { new ObjetVital("Eau usée", 1), new ObjetVital("Soda", 1),
			new ObjetVital("Viande cru", 1), new ObjetVital("Sirop pour la toux", 1), new ObjetVital("Bandage", 1),
			new Arme("Pistolet"), new Arme("Couteau"), new Arme("Marteau"), new ObjetDivers("Combustible", 1),
			new ObjetDivers("Allumette", 1) };

	/**
	 * Constructeur
	 * 
	 * @param s
	 * @param d
	 * @param o
	 * @param m
	 */
	public Case(char s) {
		this.symbole = s;
		attribution(s);
	}

	/**
	 * Attribut le contenu de la case selon son symbole
	 * 
	 * @param symbole
	 */
	public void attribution(char symbole) {
		int nb;
		switch (symbole) {

		case '*': // zone hostile
			setDescription("Zone hostile");
			nb = outils.alea(0, 3);
			if (nb == 2) { // 1 chance sur 3 de trouver un objet
				nb = outils.alea(0, objetZoneHostile.length);
				setObjet(objetZoneHostile[nb]);
			}

			break;
		case '#': // zone sécurisé
			nb = outils.alea(0, 5);
			setDescription("Zone sécurisé");
			if (nb == 2) { // 1 chance sur 5 de trouver un objet
				nb = outils.alea(0, objetZoneSecurise.length);
				setObjet(objetZoneSecurise[nb]);
			}

			break;
		case '$':
			setDescription("Stalker");
			break;

		case '@':
			setDescription("Refuge");
			break;
		default:
			System.out.println("Type de case non défini");
			break;
		}

	}

	/**
	 * Place visuellement le joueur sur cette case
	 */
	public void placerPerso() {
		setSymbole('$');
	}

	/**
	 * Permet de remettre le symbole de départ correspond à cette case
	 */
	public void symboleOriginal() {
		if (this.description == "Zone hostile") {
			setSymbole('*');
		} else if (this.description == "Refuge") {
			setSymbole('@');
		} else {
			setSymbole('#');
		}
	}

	/**
	 * Getters et Setters
	 */
	public char getSymbole() {
		return symbole;
	}

	public void setSymbole(char symbole) {
		this.symbole = symbole;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Objet getObjet() {
		return objet;
	}

	public void setObjet(Objet objet) {
		this.objet = objet;
	}

}
