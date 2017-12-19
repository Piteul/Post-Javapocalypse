package Autre;

import Outils.Outils;
import Personnage.Personnage;

/**
 * Classe qui définit les différentes météos
 * 
 * @author Axel Tétart
 *
 */

public class Météo {
	Outils outils = new Outils();
	private int coutPV;
	private int coutEnd;
	private String nom;
	String tabNom[] = { "Vaste grisaille", "Pluie radioactive", "Grand froid", "Grêles tranchantes",
			"Soleil vert" };

	Personnage perso;

	/**
	 * Constructeur
	 * 
	 * @param nom
	 */

	public Météo(Personnage p) {
		perso = p;
		rendementAleatoire();
	}

	/**
	 * Détermine de manière aléatoire la météo
	 */
	public void rendementAleatoire() {
		int nb = outils.alea(0, 10);
		switch (nb) {
		case 4:
		case 8:
			setNom(tabNom[1]); // Pluie radioactive
			coutPV = (int) Math.round(perso.getPointVie() * 0.05);
			coutEnd = 0;
			break;
		case 6:
			setNom(tabNom[2]); // Grand froid
			coutPV = 0;
			coutEnd = (int) Math.round(perso.getEndurance() * 0.1);
			break;
		case 2:
			setNom(tabNom[3]); // Grêles tranchantes
			coutPV = (int) Math.round(perso.getPointVie() * 0.1);
			coutEnd = (int) Math.round(perso.getEndurance() * 0.05);
			break;
		case 3:
		case 9:
			setNom(tabNom[4]); // Soleil vert
			System.out.println(getNom());
			System.out.println(perso.getEndurance());
			System.out.println(coutEnd);
			coutPV = 0;
			coutEnd = (int) Math.round(perso.getEndurance() * 0.05);
			break;
		default:
			setNom(tabNom[0]); // Vaste grisaille
			coutPV = 0;
			coutEnd = 0;
			break;
		}

	}

	/**
	 * Détermine les effet que la météo aura sur le personnage
	 * 
	 * @param nom
	 */

	public void action() {
		switch (getNom()) {

		case "Vaste grisaille":
			perso.setEndurance(perso.getEndurance()-1);
			break;
		case "Pluie radioactive":
			perso.setPointVie(perso.getPointVie() - coutPV);
			break;
		case "Grand froid":
			perso.setEndurance(perso.getEndurance() - coutEnd);
			break;
		case "Grêles tranchantes":
			perso.setPointVie(perso.getPointVie() - coutPV);
			perso.setEndurance(perso.getEndurance() - coutEnd);
			break;
		case "Soleil vert":
			perso.setEndurance(perso.getEndurance() - coutEnd);
			break;
		default:
			System.out.println("Météo inexistante");
			break;
		}
	}

	/**
	 * Affiche la météo et les coûts relatifs.
	 */
	public void afficherMeteo() {
		System.out.println("Météo actuelle : " + nom + ", Point(s) de vie : -" + coutPV + ", Endurance : -" + coutEnd);
	}

	/*
	 * Getters & Setters
	 */

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

}
