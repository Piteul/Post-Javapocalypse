package Personnage;

import Objet.Objet;

public class Personnage extends Humain {

	// CARACTERISTIQUES

	static String nom = "Stalker";
	static int vie = 100;
	static int status = 0;
	static int force = 15;
	static int defense = 20;
	double survie = 1.2; // à changer
	int endurance = 100;
	Objet inventaire[] = new Objet[10];

	/**
	 * Constructeur
	 */

	public Personnage() {
		super(nom, vie, status, force, defense);
	}

	/**
	 * Partie exploration
	 */

	// sur la carte
	public void seDeplacer() {

	}

	public void fouiller() {

	}

	public void voirInventaire() {

	}

	// dans l'inventaire
	public void utiliserObjet() {

	}

	public void jeterObjet() {

	}

	/**
	 * Partie Combat
	 */

	public void attaquer() {

	}

	public void seDefendre() {

	}

	public void fuir() {

	}

	/**
	 * Getters et Setters
	 */
	public double getSurvie() {
		return survie;
	}

	public void setSurvie(double survie) {
		this.survie = survie;
	}

	public int getEndurance() {
		return endurance;
	}

	public void setEndurance(int endurance) {
		this.endurance = endurance;
	}

	public Objet[] getInventaire() {
		return inventaire;
	}

	public void setInventaire(Objet[] inventaire) {
		this.inventaire = inventaire;
	}
}
