package Affichage;

import Objet.Arme;
import Objet.Objet;
import Objet.ObjetDivers;
import Objet.ObjetVital;
import Outils.Outils;
import Personnage.Monstre;

public class Case {

	Outils outils;
	private char symbole;
	private String description;
	private Objet objet;

	Objet objetZoneHostile[] = { new ObjetVital("Haricot en boite", 1), new ObjetVital("Eau usée", 1),
			new ObjetVital("Eau propre", 1), new ObjetVital("Soda", 1), new ObjetVital("Viande cru", 1),
			new ObjetVital("Sirop pour la toux", 1), new ObjetVital("Bandage", 1), new ObjetVital("Morphine", 1),
			new ObjetVital("Vitamine C", 1), new Arme("Pistolet"), new Arme("Fusil à pompe"), new Arme("Couteau"),
			new Arme("Marteau"), new Arme("Machette"), new ObjetDivers("Combustible", 1),
			new ObjetDivers("Allumette", 1) };

	Objet objetZoneSecurise[] = { new ObjetVital("Eau usée", 1), new ObjetVital("Soda", 1),
			new ObjetVital("Viande cru", 1), new ObjetVital("Sirop pour la toux", 1), new ObjetVital("Bandage", 1),
			new Arme("Pistolet"), new Arme("Couteau"), new Arme("Marteau"), new ObjetDivers("Combustible", 1),
			new ObjetDivers("Allumette", 1) };

	public Case(char s, String d, Objet o, Monstre m) {
		this.symbole = s;
	}

	public void atribution(char symbole) {
		switch (symbole) {

		case '*': // zone hostile
			nb = outils.alea(0, 10);
			setDescription("Zone hostile");

			break;
		case '#':
			nb = outils.alea(0, 10);
			setDescription("Zone sécurisé");

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
	 * Créer un objet d'après un nombre aléatoire
	 */
	public void genererObjet(int nb) {

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
