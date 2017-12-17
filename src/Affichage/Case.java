package Affichage;

import Objet.Objet;
import Outils.Outils;
import Personnage.Monstre;

public class Case {

	Outils outils;
	private char symbole;
	private String description;
	private Objet objet;
	private Monstre monstre;

	public Case(char s, String d, Objet o, Monstre m) {
		this.symbole = s;
	}

	public void atribution(char symbole) {
		int nb;
		switch (symbole) {

		case '*': // zone hostile
			nb = outils.alea(0, 10);
			setDescription("Zone hostile");

			if (nb > 5) { // a changer
				setMonstre(null);
				genererObjet(nb);

			} else {
				genererMonstre(nb);
				setObjet(null);
			}
			break;

		case '#':
			nb = outils.alea(0, 10);
			setDescription("Zone sécurisé");
			setMonstre(null);
			
			if(nb > 5) {
				
			}
			break;

		case '$':
			setDescription("Stalker");
			break;

		case '@':
			setDescription("Refuge");

		default:
			System.out.println("Type de case non défini");
			break;
		}

	}

	/**
	 * Créer un monstre d'après un nombre aléatoire
	 */
	public void genererMonstre(int nb) {

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

	public Monstre getMonstre() {
		return monstre;
	}

	public void setMonstre(Monstre monstre) {
		this.monstre = monstre;
	}

}
